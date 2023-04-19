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
    private String licencePlate;
    @Column(name="vehicle_licence")
    private int vehicleLicence;
    @Column(name="color_id")
    private int colorId;
    @Column(name="relase_year") //TODO modify name in database
    private int releaseYear;
    @Column(name="car_model_id")
    private int carModelId;

    @Column(name = "car_maker_id")
    public int carMakerId;

    public Vehicle() {
    }

    public Vehicle(String licencePlate, int vehicleLicence, int colorId, int releaseYear, int carModelId, int carMakerId) {
        this.licencePlate = licencePlate;
        this.vehicleLicence = vehicleLicence;
        this.colorId = colorId;
        this.releaseYear = releaseYear;
        this.carModelId = carModelId;
        this.carMakerId = carMakerId;
    }


   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
*/
    @Column(name="licence_plate")
    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }


    @Column(name="vehicle_licence")
    public int getVehicleLicence() {
        return vehicleLicence;
    }

    public void setVehicleLicence(int vehicleLicence) {
        this.vehicleLicence = vehicleLicence;
    }

    @Column(name="color_id")
    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    @Column(name="relase_year")
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Column(name = "car_model_id")
    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

   /* @Column(name = "car_maker_id")
    public int getCarMakerId() {
        return carMakerId;
    }

    public void setCarMakerId(int carMakerId) {
        this.carMakerId = carMakerId;
    }*/
}
