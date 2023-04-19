package com.uber.uber.service;

import com.uber.uber.models.Vehicle;
import com.uber.uber.repository.VehicleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VehicleService {
    @Autowired
    private VehicleRepo repo;

    public List<Vehicle> getVehicles(){
        return repo.findAll();
    }

    public Vehicle getVehicleById(int id){
        return repo.findById(id).get();
    }

    public void save(Vehicle vehicle){
        repo.save(vehicle);
    }

}
