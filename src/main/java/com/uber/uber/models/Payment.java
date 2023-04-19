package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id // this means the variable is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indicate it's auto generated
    public int id;
    public String value;
    @Column(name = "paymentmethod_id")
    public int paymentMethodId;

    public Payment() {
    }

    public Payment(String value, int paymentMethodId) {
        this.value = value;
        this.paymentMethodId = paymentMethodId;
    }
}
