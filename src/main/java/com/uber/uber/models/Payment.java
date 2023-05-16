package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {
    @Id // this means the variable is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indicate it's auto generated
    @Column(name = "id",nullable = false)
    public int id;
    @Column(name = "value")
    public float value;
    @Column(name = "paymentmethod_id")
    public int paymentMethodId;

    @Column(name = "rider_id")
    public int riderId;

    @Column(name = "trip_id")
    public int tripId;

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

    public Payment() {
    }

    public Payment(float value, int paymentMethodId, int riderId, int tripId) {
        this.value = value;
        this.paymentMethodId = paymentMethodId;
        this.riderId = riderId;
        this.tripId = tripId;

    }
}
