package com.uber.uber.service;

import com.uber.uber.form.TripForm;
import com.uber.uber.models.Trip;
import com.uber.uber.repository.TripRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TripService {
    @Autowired
    private TripRepo repo;

    public List<Trip> getTrips(){
        return repo.findAll();
    }

    public Trip getTripById(int id){
        return repo.findById(id).get();
    }
    public Trip getTripByRiderId(int riderId) {
        return repo.findByRiderId(riderId);
    }

    public Trip save(Trip trip){
        return repo.save(trip);
    }

}
