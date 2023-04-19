package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "release_year")
public class ReleaseYear {
    @Id
    public int year;
    public ReleaseYear() {}
}
