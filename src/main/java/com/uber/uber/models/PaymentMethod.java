package com.uber.uber.models;

import jakarta.persistence.*;

@Entity
@Table(name = "paymentmethod")
public class PaymentMethod {
    @Id // this means the variable is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indicate it's auto generated
    public int id;
    public String type;

    public PaymentMethod() {
    }

    public PaymentMethod(String type) {
        this.type = type;
    }
}
