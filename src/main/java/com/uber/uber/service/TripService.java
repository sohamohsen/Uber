package com.uber.uber.service;

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

    public Trip getTripByAccountId(int id){
        return repo.findById(id).get();
    }
    public List<Trip> getRequestedTripByDriverId(int driverId) {
        return repo.findByDriverIdAndStatusId(driverId,4);
    }

    public Trip getTripByStatusId(int statusId) {
        return repo.findByStatusId(statusId);
    }
    public Trip getTripById(int id){
        return repo.findById(id).get();
    }
    public List<Trip> getTripByRiderId(int riderId) {
        return repo.findByRiderId(riderId);
    }
    public List<Trip> getTripByDriverId(int driverId) {
        return repo.findByDriverId(driverId);
    }

    public List<Trip> getRiderRequestedTrips(int riderId) {
        return repo.findByRiderIdAndStatusId(riderId, 4);
    }


    public Trip updateStatus(int id, int statusId){
        Trip trip = null;
        try {
            trip = repo.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (trip != null){
            trip.statusId = statusId;
            return repo.save(trip);
        }else {
            return null;
        }

    }

    public Trip save(Trip trip){
        return repo.save(trip);
    }

}
