package com.uber.uber.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="vehicle")
public class Vehicle implements Serializable {

    @Id // this means the variable is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indicate it's auto generated
    public int id;

    @Column(name="licence_plate")
    public String licencePlate;
    @Column(name="vehicle_licence")
    public int vehicleLicence;
    @Column(name="color_id")
    public int colorId;
    @Column(name="relase_year") //TODO modify name in database
    public int releaseYear;
    @Column(name="car_model_id")
    public int carModelId;


    @Column(name = "car_maker_id")
    public int carMakerId;

    @Column(
            name = "driver_id"
    )
    public int driverId;

    /*@OneToOne
    @JoinColumn(
            name = "driver_id"
    )
    public Driver driver;*/

    public Vehicle() {
    }

    public Vehicle(String licencePlate, int vehicleLicence, int colorId, int releaseYear, int carModelId, int carMakerId, int driverId) {
        this.licencePlate = licencePlate;
        this.vehicleLicence = vehicleLicence;
        this.colorId = colorId;
        this.releaseYear = releaseYear;
        this.carModelId = carModelId;
        this.carMakerId = carMakerId;
        this.driverId = driverId;
    }
}
