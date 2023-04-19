package com.uber.uber.service;

import com.uber.uber.models.Transaction;
import com.uber.uber.models.Trip;
import com.uber.uber.repository.TransactionRepo;
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

    public void save(Trip trip){
        repo.save(trip);
    }

}
