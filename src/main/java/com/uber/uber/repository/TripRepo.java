package com.uber.uber.repository;

import com.uber.uber.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepo extends JpaRepository<Trip, Integer> {
}
