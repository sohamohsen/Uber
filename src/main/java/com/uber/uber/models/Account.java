package com.uber.uber.models;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "account")
public class Account {

    private int id;
    private String email;
    private String password;
    private String type;

    public Account() {
    }

    //write all arrgs needed when create new record into DB
    public Account(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
