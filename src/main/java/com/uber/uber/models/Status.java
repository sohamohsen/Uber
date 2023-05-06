package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    public int id;
    @Column(name = "status")
    public String status;
    @ManyToMany(
            mappedBy = "status"
    )
    @JsonIgnore
    public List<Trip> trips;

    public Status() {
    }

    public Status(String status) {
        this.status = status;
    }

}
