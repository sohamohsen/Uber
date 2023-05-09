package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

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

    @ManyToMany(
            mappedBy = "carModel"
    )
    @JsonIgnore
    public List<Vehicle> vehicle;

    public CarModel() {
    }

    public CarModel(int carMakerId, String name) {
        this.carMakerId = carMakerId;
        this.name = name;
    }

}
