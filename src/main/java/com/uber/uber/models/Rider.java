package com.uber.uber.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="rider")
public class Rider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public String lastName;

    @Column(name = "account_id")
    public int accountId;
    @Column(name = "phone_number")
    public String phoneNumber;

    @Column(name = "birthdate")
    public Date birthdate;

    @Column(name = "city_id")
    public int cityId;


    public Rider() {
    }

}
