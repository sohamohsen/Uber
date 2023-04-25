package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "rider_id",
            referencedColumnName = "id",
            updatable = false,
            insertable = false
    )
    @JsonIgnore
    public Rider rider;

    public RiderWallet() {
    }


}
