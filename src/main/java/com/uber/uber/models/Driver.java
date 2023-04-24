package com.uber.uber.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "driver")
public class Driver {
    @Id // this means the variable is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indicate it's auto generated
    public int id;
    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public String lastName;
    @Column(name = "phone_number")
    public String phoneNumber;
    @Column(name = "national_id")
    public int nationalId;
    @Column(name = "driver_licence")
    public int driverLicence;
    @Column(name = "gender")
    public int gender;
    @Column(name = "birth_date")
    public Date birthDate;

    @Column(name = "avalible")
    public boolean available;
    @Column(name = "city_id")
    public int cityId;
    @Column(name = "account_id")
    public int accountId;

    public Driver() {
    }

    public Driver(String firstName, String lastName, String phoneNumber, int nationalId, int driverLicence, int gender, Date birthDate, boolean available, int cityId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.driverLicence = driverLicence;
        this.gender = gender;
        this.birthDate = birthDate;
        this.available = available;
        this.cityId = cityId;
    }
}
