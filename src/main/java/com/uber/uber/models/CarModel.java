package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carmodel")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "car_maker_id")
    public int carMakerId;

    @Column(name = "name")
    public String name;
    public CarModel() {
    }

    public CarModel(int carMakerId, String name) {
        this.carMakerId = carMakerId;
        this.name = name;
    }

}
