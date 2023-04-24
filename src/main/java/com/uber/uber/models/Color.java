package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "color")
public class Color {
    @Id
    public int id;
    public String color;

    public Color() {
    }

    public Color(String color) {
        this.color = color;
    }
}



