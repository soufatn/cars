package com.cars.model;

import javax.persistence.*;

@Entity(name = "carOrder")
public class Order {
    @Id
    @GeneratedValue
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

    public Order(String email, Car car, int price) {
        this.email = email;
        this.car = car;
        this.price = price;
    }

    public static Order of(int id, String email, Car car, int price) {
        return new Order(id, email, car, price);
    }

    public static Order of(String email, Car car, int price) {
        return new Order(email, car, price);
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Car getCar() {
        return car;
    }

    public Integer getPrice() {
        return price;
    }
}
