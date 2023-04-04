package com.cars.model;

import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String email;

    private Client(String email) {

        this.email = email;
    }

    private Client(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    private Client() {
    }

    public static Client of(String email) {
        return new Client(email);
    }

    public static Client of(int id, String email) {
        return new Client(id, email);
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
