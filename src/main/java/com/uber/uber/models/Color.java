package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "color")
public class Color {
    private int id;
    private String color;

    public Color() {
    }

    public Color(String color) {
        this.color = color;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
