package com.uber.uber.repository;

import com.uber.uber.models.Trip;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TripRepo extends JpaRepository<Trip, Integer> {
    List<Trip> findByRiderId(int riderId);
    List<Trip> findByDriverIdAndStatusId(int driverId,int statusId);
    List<Trip> findByRiderIdAndStatusId(int riderId,int statusId);

    List<Trip> findByDriverId(int driverId);
    Trip findByStatusId(int statusId);


}
