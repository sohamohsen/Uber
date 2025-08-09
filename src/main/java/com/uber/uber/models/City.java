package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name="city")
public class City {

                                           /****  declaration of attributes  ****/
    /*Announce that this colum is the primary key*/
    @Id
    //Announce that this id generate automatically in the process of creating new account.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Nullable to announce that this attribute can't be null.
    public int id;

    @Column(name="city_name")
    public String cityName;


    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

}
