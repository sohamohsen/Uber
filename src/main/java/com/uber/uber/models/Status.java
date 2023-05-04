package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    public int id;
    @Column(name = "status")
    public String status;

    public Status() {
    }

    public Status(String status) {
        this.status = status;
    }

}
