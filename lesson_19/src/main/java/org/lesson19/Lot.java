package org.lesson19;

import jakarta.persistence.*;

@Entity
@Table(name = "lot")
public class Lot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    public Lot(Long id, String title) {
        this.id = id;
        this.title = title;
        this.price = 0;
    }

    @Version
    private Long version;

    public Lot() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", user=" + user +
                '}';
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    public void setUser(User user) {
        this.user = user;
    }
    // ссылку на владельца последней ставки.
}
