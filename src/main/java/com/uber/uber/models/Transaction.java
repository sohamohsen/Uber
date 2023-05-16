package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id // this means the variable is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indicate it's auto generated
    @Column(name = "id",nullable = false)
    public int id;
    @Column(name = "trip_id")
    public int tripId;


    @Column(name = "value")
    public float value;
    @Column(name = "driver_id")
    public int driverId;
    @Column(name = "create_date")
    @CreationTimestamp
    public Date createDate;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "trip_id",
            referencedColumnName = "id",
            updatable = false,
            insertable = false
    )
    @JsonIgnore
    public Trip trip;

    public Transaction() {
    }

    public Transaction(int tripId, float value, int driverId) {
        this.tripId = tripId;
        this.value = value;
        this.driverId = driverId;
    }
}
