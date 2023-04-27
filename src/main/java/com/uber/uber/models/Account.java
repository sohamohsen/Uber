package com.uber.uber.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;




@Entity
@Table(name = "account")
public class Account  {

    //declaration of attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)    //nullable to announce that this attribute can't be null
    public int id;
    @Column(name = "email")
    public String email;
    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)    //announce that password mustn't be sent with json file
    public String password;
    @Column(name = "type")
    public String type;


        //declaration of relationships
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

    //write all arguments needed when create new record into DB

    public Account(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

}
