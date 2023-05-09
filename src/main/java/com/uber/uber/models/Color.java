package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "color")
public class Color {
    @Id
    public int id;
    public String color;

    @ManyToMany(
            mappedBy = "color"
    )
    @JsonIgnore
    public List<Vehicle> vehicle;


    public Color() {
    }

    public Color(String color) {
        this.color = color;
    }
}



