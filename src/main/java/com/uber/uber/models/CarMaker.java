package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carmaker")
public class CarMaker {
    public int id;
    public String maker;

    public CarMaker() {
    }

    public CarMaker(String maker) {
        this.maker = maker;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }
}
