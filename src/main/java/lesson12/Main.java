package lesson12;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class ReflectionRepository<T> {
    private Connection connection;
    private Class<T> objClass;
    private Constructor<T> constructor;
    private Field id;
    private List<Field> fields;
    private List<Field> fieldsWithoutKey;
    private String tableName;

    private String sqlInsertOne;
    private String sqlDeleteOne;
    private String sqlDeleteAll;
    private String sqlSelectOne;
    private String sqlSelectAll;

    private String createSql_insertOne() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(tableName);
        sql.append(" ");
        sql.append(fieldsWithoutKey.stream().map(Field::getName).collect(Collectors.joining(",", "(", ")")));
        sql.append(" VALUES ");
        sql.append(fieldsWithoutKey.stream().map(field -> "?").collect(Collectors.joining(",", "(", ")")));
        sql.append(";");
        return sql.toString();
    }

    private String createSql_deleteOne() {
        return "DELETE FROM %s WHERE id=?;".formatted(tableName);
    }

    private String createSql_deleteAll() {
        return "DELETE FROM %s;".formatted(tableName);
    }

    private String createSql_selectOne() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(fields.stream().map(Field::getName).collect(Collectors.joining(", ")));
        sql.append(" FROM ");
        sql.append(tableName);
        sql.append(" WHERE ");
        sql.append(id.getName());
        sql.append(" = ?;");
        return sql.toString();
    }

    private String createSql_selectAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(fields.stream().map(Field::getName).collect(Collectors.joining(", ")));
        sql.append(" FROM ");
        sql.append(tableName);
        sql.append(";");
        return sql.toString();
    }

    private void checkConnection() {
        try {
            if (connection != null && connection.isClosed()) {
                throw new RuntimeException("Нет соединения с БД");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ReflectionRepository(Class<T> objClass, Connection connection) {
        checkConnection();

        this.connection = connection;
        this.objClass = objClass;
        if (!objClass.isAnnotationPresent(DbTable.class)) {
            throw new RuntimeException("У класса нет аннотации @DbTable");
        }

        tableName = objClass.getAnnotation(DbTable.class).name();

        try {
            constructor = objClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("У класса нет конструктора по умолчанию");
        }

        List<Field> ids = Arrays.stream(objClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(DbId.class)).collect(Collectors.toList());
        if (ids.size() != 1 || ids.get(0).getType() != Long.class) {
            throw new RuntimeException("У класса должен быть один первичный ключ с аннотацией @DbId и типом Long");
        }

        id = ids.get(0);

        fields = Arrays.stream(objClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(DbColumn.class)).collect(Collectors.toList());
        fieldsWithoutKey = fields.stream().filter(field -> !field.isAnnotationPresent(DbId.class)).collect(Collectors.toList());

        sqlInsertOne = createSql_insertOne();
        sqlDeleteOne = createSql_deleteOne();
        sqlDeleteAll = createSql_deleteAll();
        sqlSelectOne = createSql_selectOne();
        sqlSelectAll = createSql_selectAll();
    }

    T createObject() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return constructor.newInstance();
    }

    public T addItem(T item) throws IllegalAccessException {
        String[] generatedColumns = {id.getName()};
        try (var pst = connection.prepareStatement(sqlInsertOne, generatedColumns)) {
            int ind = 1;
            for (Field field : fieldsWithoutKey) {
                pst.setObject(ind++, field.get(item));
            }
            pst.execute();

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id.set(item, generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    public void removeById(Long id) {
        try (var pst = connection.prepareStatement(sqlDeleteOne)) {
            pst.setLong(1, id);
            pst.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTable() {
        try (var st = connection.createStatement()) {
            st.executeUpdate(sqlDeleteAll);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<T> getItem(Long id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try (var pst = connection.prepareStatement(sqlSelectOne)) {
            pst.setLong(1, id);
            try (var rs = pst.executeQuery()) {
                if (rs.next()) {
                    T result = createObject();
                    for (var field : fields) {
                        field.set(result, rs.getObject(field.getName()));
                    }
                    return Optional.of(result);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<T> getAll() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var res = new ArrayList<T>();
        try (var st = connection.createStatement()) {
            try (var rs = st.executeQuery(sqlSelectAll)) {
                rs.setFetchSize(100);
                while (rs.next()) {
                    T result = createObject();
                    for (var field : fields) {
                        field.set(result, rs.getObject(field.getName()));
                    }
                    res.add(result);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

}

@DbTable(name = "person")
class Person {
    public Person() {
    }

    public Person(String fio, String phone, Integer age, Long tempData) {
        this.fio = fio;
        this.phone = phone;
        this.age = age;
        this.tempData = tempData;
    }

    @DbId
    @DbColumn
    Long id;
    @DbColumn
    String fio;
    @DbColumn
    String phone;
    @DbColumn
    Integer age;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", tempData=" + tempData +
                '}';
    }

    Long tempData;
}

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")) {
            ReflectionRepository<Person> repository = new ReflectionRepository<>(Person.class, connection);
            var p1 = repository.addItem(new Person());
            var p2 = repository.addItem(new Person("Петр", "+7283746483", 22, 17234L));

            System.out.println(p1);
            System.out.println(p2);

            var person = repository.getItem(47L).orElseThrow(RuntimeException::new);
            System.out.println(person.toString());

            var allPersons = repository.getAll();
            System.out.println(allPersons);
            //   repository.removeById(40L);
            //   repository.clearTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
