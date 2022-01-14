package com.cars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    private Integer id;

    @Column
    private String email;

    private Client(String email) {

        this.email = email;
    }

    public static Client of(String email) {
        return new Client(email);
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
