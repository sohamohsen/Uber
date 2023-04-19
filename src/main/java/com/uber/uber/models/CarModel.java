package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carmodel")
public class CarModel {
    private int id;
    @Column(name = "car_maker_id")
    private int carMakerId;
    private String name;
    public CarModel() {
    }

    public CarModel(int carMakerId, String name) {
        this.carMakerId = carMakerId;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarMakerId() {
        return carMakerId;
    }

    public void setCarMakerId(int carMakerId) {
        this.carMakerId = carMakerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
