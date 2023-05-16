package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

                                /****Identification table****/
@Entity
@Table(name = "carmaker")
public class CarMaker {
                                    /****  declaration of attributes  ****/
    /*Announce that this colum is the primary key*/
    @Id
    /*Announce that this id generate automatically in the process of creating new account*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Nullable to announce that this attribute can't be null*/
    @Column(name = "id",nullable = false)
    /**Doing attributes public not private due to changing of name from column name**/
    public int id;

    @Column(name = "maker")
    public String maker;

                            /****Declaration of relationships between tables****/
    // 1- Determine the type of relationship.
    // 2-
    // 3-

    /***Relation Many to Many with Vehicle table. ***/
    @ManyToMany(
            mappedBy = "carMaker"
    )
    @JsonIgnore
    public List<Vehicle> vehicle;

    public CarMaker() {
    }

              /**** All arguments needed when create new record into DB ****/
    public CarMaker(String maker) {
        this.maker = maker;
    }
}
