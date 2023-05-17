package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


                              /****  Identification table  ****/
@Entity
@Table(name = "account")
public class Account  {


                           /****  declaration of attributes  ****/
    /*Announce that this colum is the primary key*/
    @Id
    /*Announce that this id generate automatically in the process of creating new account*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Nullable to announce that this attribute can't be null*/
    @Column(name = "id",nullable = false)
    /*Doing attributes public not private due to changing of name from column name*/
    public int id;

    @Column(name = "email")
    public String email;

    @Column(name = "password")
    /*Announce that password mustn't be sent with json file*/
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

    @Column(name = "type")
    public String type;


    /*Declaration of relationships between tables*/
        // 1- Determine the type of relationship.
       // 2-
      // 3-
    // Relation one to one with Driver table.
    @OneToOne(
            mappedBy = "account"
    )
    public Driver driver;

    // Relation one to one with Rider table
    @OneToOne(
            mappedBy = "account"
    )
    public Rider rider;


    /**** All arguments needed when create new record into DB ****/
    public Account(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public Account() {
    }
}
