package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "riderwallet")
public class RiderWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "balance")
    public float balance;
    @Column(name = "rider_id")
    public int riderId;

    public RiderWallet(float balance, int riderId) {
        this.balance = balance;
        this.riderId = riderId;
    }

    public RiderWallet() {
    }


}
