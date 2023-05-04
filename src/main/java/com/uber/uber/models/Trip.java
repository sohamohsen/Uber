package com.uber.uber.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    public int id;
    @Column(name = "act_pick_loc_lat")
    public double actPickLocLat ;
    @Column(name = "act_pick_loc_lng")
    public double actPickLocLng ;
    @Column(name = "pick_loc_lng")
    public double pickLocLng ;
    @Column(name = "pick_loc_lat")
    public double pickLocLat ;
    @Column(name = "act_drop_loc_lng")
    public double actDropLocLng ;
    @Column(name = "act_drop_loc_lat")
    public double actDropLocLat ;
    @Column(name = "drop_loc_lng")
    public double dropLocLng ;
    @Column(name = "drop_loc_lat")
    public double dropLocLat ;
    @Column(name = "pick_time")
    public Date pickTime ;
    @Column(name = "drop_time")
    public Date dropTime ;


    @Column(name = "create_date")
    @CreationTimestamp
    public Date createDate ;// database will create it for you, auto creation
    @Column(name = "fare")
    public float fare ;
    @Column(name = "duration")
    public float duration ;
    @Column(name = "distance")
    public float distance ;
    @Column(name = "status_id")
    public int statusId ;
    @Column(name = "driver_id")
    public int driverId ;
    @Column(name = "rider_id")
    public int riderId;

    public Trip() {
    }

    public Trip(double pickLocLng, double pickLocLat, double dropLocLng, double dropLocLat, int statusId, int driverId, int riderId) {
        this.pickLocLng = pickLocLng;
        this.pickLocLat = pickLocLat;
        this.dropLocLng = dropLocLng;
        this.dropLocLat = dropLocLat;
        this.statusId = statusId;
        this.driverId = driverId;
        this.riderId = riderId;
    }
}
