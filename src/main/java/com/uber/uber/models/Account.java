package com.uber.uber.models;


import jakarta.persistence.*;




@Entity
@Table(name = "account")
public class Account  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    public int id;
    @Column(name = "email")
    public String email;
    @Column(name = "password")
    public String password;
    @Column(name = "type")
    public String type;


    @OneToOne(
            mappedBy = "account"
    )
    public Driver driver;

    @OneToOne(
            mappedBy = "account"
    )
    public Rider rider;


    public Account() {
    }

    //write all arrgs needed when create new record into DB

    public Account(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }


}
