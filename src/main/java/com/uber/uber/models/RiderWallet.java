package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "riderwallet")
public class RiderWallet {
    private int id;
    private float balance;
    @Column(name = "rider_id")
    private int riderId;

    public RiderWallet(float balance, int riderId) {
        this.balance = balance;
        this.riderId = riderId;
    }

    public RiderWallet() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getRiderId() {
        return riderId;
    }

    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }
}
