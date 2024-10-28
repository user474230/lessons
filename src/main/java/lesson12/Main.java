package lesson12;

import java.sql.*;

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
