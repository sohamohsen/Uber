package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name="city")
public class City {
    //declaration of attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id",nullable = false)    //nullable to announce that this attribute can't be null
    public int id;

    @Column(name="city_name")
    public String cityName;

    //declaration of relationships
    /*@OneToOne(
            mappedBy = "city"
    )
    public Driver driver;

    @OneToOne(
            mappedBy = "city"
    )
    public Rider rider;*/

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }




}
