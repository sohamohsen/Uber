package com.uber.uber.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "trip")
public class Trip {
    @Id // this means the variable is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indicate it's auto generated
    public int id;
    public double act_pick_loc ;
    public double pick_loc ;
    public double act_drop_loc ;
    public double drop_loc ;
    public Date pick_time ;
    public Date drop_time ;
    public Date create_date ;
    public float fare ;
    public float duration ;
    public float distance ;
    public int status_id ;
    public int driver_id ;
    public int rider_id;

    public Trip() {
    }

    public Trip(double act_pick_loc, double pick_loc, double act_drop_loc, double drop_loc, Date pick_time, Date drop_time, Date create_date, float fare, float duration, float distance, int status_id, int driver_id, int rider_id) {
        this.act_pick_loc = act_pick_loc;
        this.pick_loc = pick_loc;
        this.act_drop_loc = act_drop_loc;
        this.drop_loc = drop_loc;
        this.pick_time = pick_time;
        this.drop_time = drop_time;
        this.create_date = create_date;
        this.fare = fare;
        this.duration = duration;
        this.distance = distance;
        this.status_id = status_id;
        this.driver_id = driver_id;
        this.rider_id = rider_id;
    }
}
