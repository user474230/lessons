package org.lesson19;

import jakarta.persistence.*;

@Entity
@Table(name = "my_user")
public class User {
    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "fio")
    String fio;

    public Long getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public User(Long id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                '}';
    }
}
