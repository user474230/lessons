package lesson12;

@DbTable(name = "person")
class Person {

    @DbId
    @DbColumn
    Long id;
    @DbColumn
    String fio;
    @DbColumn
    String phone;
    @DbColumn
    Integer age;

    public Person() {
    }

    public Person(String fio, String phone, Integer age, Long tempData) {
        this.fio = fio;
        this.phone = phone;
        this.age = age;
        this.tempData = tempData;
    }

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
