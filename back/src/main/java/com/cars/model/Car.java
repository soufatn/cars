package com.cars.model;

import jakarta.persistence.*;

@Entity
public class Car {
    public static final int SMALL_CAR_MAX_PRICE = 15000;
    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private String category;

    private Car(String name, String category) {
        this.name = name;
        this.category = category;
    }

    private Car() {

    }

    private Car(int id, String name, int price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    private Car(String name, int price, String category) {
        this(0, name, price, category);
    }

    public static Car of(String name, String category) {
        return new Car(name, category);
    }

    public static Car of(String name, int price, String category) {
        return new Car(name, price, category);
    }

    public static Car of(int id, String name, int price, String category) {
        return new Car(id, name, price, category);
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
