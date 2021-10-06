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

    public Car(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Car() {

    }

    public static Car of(String name, String category) {
        return new Car(name, category);
    }

    public String getCategory() {
        return category;
    }

    private String initializeCategory() {
        if (price < 15000) {
            return "Small";
        } else {
            return "Family";
        }
    }
}
