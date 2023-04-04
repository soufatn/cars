package com.cars.model;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    @Column
    private LocalDate orderDate;

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

    public Order(String email, Car car, int price, LocalDate orderDate) {
        this.email = email;
        this.car = car;
        this.price = price;
        this.orderDate = orderDate;
    }

    public Order(int id, String email, Car car, int price, LocalDate orderDate) {
        this.id = id;
        this.email = email;
        this.car = car;
        this.price = price;
        this.orderDate = orderDate;
    }

    public static Order of(int id, String email, Car car, int price) {
        return new Order(id, email, car, price);
    }

    public static Order of(String email, Car car, int price, LocalDate orderDate) {
        return new Order(email, car, price, orderDate);
    }

    public static Order of(String email, Car car, int price) {
        return new Order(email, car, price);
    }

    public static Order of(int id, String email, Car car, int price, LocalDate orderDate) {
        return new Order(id, email, car, price, orderDate);
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

    public boolean isSame(Order order) {
        return this.getCar().getId().equals(order.getCar().getId()) &&
                this.getEmail().equals(order.getEmail()) &&
                this.getPrice().equals(order.getPrice());
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
