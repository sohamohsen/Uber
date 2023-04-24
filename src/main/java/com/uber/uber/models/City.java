package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name="city")
public class City {
    @Id
    public int id;

    @Column(name="city_name")
    public String cityName;

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }


}
