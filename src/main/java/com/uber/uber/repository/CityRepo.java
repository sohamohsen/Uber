package com.uber.uber.repository;

import com.uber.uber.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City,Integer> {
}
