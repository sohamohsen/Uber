package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id // this means the variable is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indicate it's auto generated
    public int id;
    @Column(name = "trip_id")
    public int tripId;
    @Column(name = "payment_id")
    public int paymentId;
    public String value;

    public Transaction() {
    }

    public Transaction(int tripId, int paymentId, String value) {
        this.tripId = tripId;
        this.paymentId = paymentId;
        this.value = value;
    }
}
