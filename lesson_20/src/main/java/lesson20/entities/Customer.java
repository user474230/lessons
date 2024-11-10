package lesson20.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
//пользователи (id, имя, возраст)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "fio")
    String fio;
    @Column(name = "age")
    Integer age;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", age=" + age +
                '}';
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Customer(String fio, Integer age) {
        this.fio = fio;
        this.age = age;
    }

    public Customer() {
    }
}
