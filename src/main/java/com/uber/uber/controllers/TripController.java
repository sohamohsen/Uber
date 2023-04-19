package com.uber.uber.controllers;

import com.uber.uber.models.Transaction;
import com.uber.uber.models.Trip;
import com.uber.uber.service.TransactionService;
import com.uber.uber.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class TripController {
    @Autowired
    private TripService service;


    @GetMapping("/trips")
    public List<Trip> getTrips() {
        return service.getTrips();
    }

}
