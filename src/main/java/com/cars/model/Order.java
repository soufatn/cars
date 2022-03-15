package com.cars.model;

import javax.persistence.*;

@Entity(name = "carOrder")
public class Order {
    @Id
    private Integer id;

    @Column
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carId")
    private Car car;

    @Column
    private Integer price;

    public Order() {
    }

    public Order(int id, String email, Car car, int price) {
        this.id = id;
        this.email = email;
        this.car = car;
        this.price = price;
    }

    public static Order of(int id, String email, Car car, int price) {
        return new Order(id, email, car, price);
    }
}
