package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

@Entity
@Table(name = "carmaker")
public class CarMaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;
    public String maker;

    @ManyToMany(
            mappedBy = "carMaker"
    )
    @JsonIgnore
    public List<Vehicle> vehicle;

    public CarMaker() {
    }

    public CarMaker(String maker) {
        this.maker = maker;
    }




    public void setMaker(String maker) {
        this.maker = maker;
    }
}
