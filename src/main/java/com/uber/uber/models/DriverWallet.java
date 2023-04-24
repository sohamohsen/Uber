package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "driverwallet")
public class DriverWallet {
    @Id // this means the variable is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indicate it's auto generated

    public int id;
    @Column(name="driver_id")
    public int driverId;
    @Column(name = "balance")
    public float balance;

    public DriverWallet() {
    }

    public DriverWallet(int driverId, float balance) {
        this.driverId = driverId;
        this.balance = balance;
    }
}
