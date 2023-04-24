package com.uber.uber.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="rider")
public class Rider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public String lastName;

    @Column(name = "account_id")
    public int accountId;
    @Column(name = "phone_number")
    public String phoneNumber;

    @Column(name = "birthdate")
    public Date birthdate;

    @Column(name = "city_id")
    public int cityId;


    public Rider() {
    }

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    @Column(name = "account_id")
    public int getAccountId() {
        return accountId;
    }


    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }*/
}
