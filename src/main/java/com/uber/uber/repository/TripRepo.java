package com.uber.uber.repository;

import com.uber.uber.models.Trip;
import com.uber.uber.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepo extends JpaRepository<Trip, Integer> {
    Trip findByRiderId(int riderId);

}
