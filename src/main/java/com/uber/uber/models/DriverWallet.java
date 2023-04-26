package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "driverwallet")
public class DriverWallet {
    @Id // this means the variable is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indicate it's auto generated
    @Column(name = "id",nullable = false)
    public int id;
    @Column(name="driver_id")
    public int driverId;
    @Column(name = "balance")
    public float balance;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "driver_id",
            referencedColumnName = "id",
            updatable = false,
            insertable = false
    )
    @JsonIgnore
    public Driver driver;

    public DriverWallet() {
    }

    public DriverWallet(int driverId, float balance) {
        this.driverId = driverId;
        this.balance = balance;
    }
}
