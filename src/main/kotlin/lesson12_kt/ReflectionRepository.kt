package lesson12_kt

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.sql.Connection

class ReflectionRepository<T>(
    objType: Class<T>,
    private val connection : Connection
) {
    private var fieldId : Field
    private var fields : List<Field>
    private var fieldsWithoutId : List<Field>
    private var constructor: Constructor<T>

    private var tableName: String

    private var sqlAddOne: String
    private var sqlDeleteOne: String
    private var sqlDeleteAll: String
    private var sqlFindOne: String
    private var sqlFindAll: String

    init {
        check(objType.isAnnotationPresent(DbTable::class.java)) {"У класса должна быть аннотация @DbTable"}

        tableName = objType.getAnnotation(DbTable::class.java).name


        fields = objType.declaredFields.filter { it.isAnnotationPresent(DbColumn::class.java) }.toList()
        fieldsWithoutId = fields.filter { !it.isAnnotationPresent(DbId::class.java) }.toList()
        constructor = objType.getConstructor();

        check(fields.size - fieldsWithoutId.size == 1) {"В классе должно быть ровно одно поле @DbId"}
        fieldId = fields.first { it.isAnnotationPresent(DbId::class.java) }

        sqlAddOne = run {
            StringBuilder()
                .append("INSERT INTO $tableName ")
                .append(fieldsWithoutId.joinToString(",", "(", ")") { it.name })
                .append(" VALUES")
                .append(fieldsWithoutId.joinToString(",", "(", ")") { "?" })
                .toString()
        }
        sqlDeleteOne = run {
            "DELETE FROM $tableName WHERE ${fieldId.name} = ?"
        }
        sqlDeleteAll = run {
            "DELETE FROM $tableName"
        }
        sqlFindOne = run {
            StringBuilder()
                .append("SELECT ")
                .append(fields.joinToString(",") { it.name })
                .append(" FROM $tableName WHERE ${fieldId.name} = ?")
                .toString()
        }
        sqlFindAll = run {
            "SELECT * FROM $tableName"
        }
    }

    fun addOne(item : T) : T {
        val generated = arrayOf(fieldId.name)
        connection.prepareStatement(sqlAddOne, generated).use { ps->
            fieldsWithoutId.forEachIndexed{ index, field ->
                ps.setObject(index + 1, field.get(item))
            }
            ps.execute()
            ps.generatedKeys.use { resultSet ->
                if (resultSet.next()) {
                    fieldId.set(item, resultSet.getLong(1))
                }
            }
        }
        return item
    }
    fun deleteOne(id : Long) {
        connection.prepareStatement(sqlDeleteOne).use { ps ->
            ps.setLong(1, id)
            ps.execute()
        }
    }
    fun deleteAll() {
        connection.createStatement().use { s ->
            s.execute(sqlDeleteAll)
        }
    }
    fun getOne(id : Long) : T {
        val ret = constructor.newInstance()
        connection.prepareStatement(sqlFindOne).use { ps ->
            ps.setLong(1, id)
            ps.executeQuery().use { resultSet ->
                if (resultSet.next()) {
                    fields.forEachIndexed{ index, field ->
                        field.set(ret, resultSet.getObject(index + 1))
                    }
                }
            }
        }
        return ret
    }
    fun getAll() : List<T>{
        val list = mutableListOf<T>()
        connection.createStatement().use { s ->
            s.executeQuery(sqlFindAll).use { resultSet ->
                while (resultSet.next()) {
                    val item = constructor.newInstance()
                    fields.forEachIndexed{index, field ->
                        field.set(item, resultSet.getObject(index + 1))
                    }
                    list.add(item)
                }
            }
        }
        return list
    }
}