package com.cars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer price;

    private String category;

    public Car(String name, Integer price) {
        this.name = name;
        this.price = price;
        this.category = initializeCategory();
    }

    private Car(String name, String category) {
        this.name = name;
        this.category = category;
    }

    private Car() {

    }

    private Car(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Car of(String name, String category) {
        return new Car(name, category);
    }

    public static Car of(String name, int price, String category) {
        return new Car(name, price, category);
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

    private String initializeCategory() {
        if (price < 15000) {
            return "Small";
        } else {
            return "Family";
        }
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
