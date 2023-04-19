package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    private int id;
    private String status;

    public Status() {
    }

    public Status(String status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
