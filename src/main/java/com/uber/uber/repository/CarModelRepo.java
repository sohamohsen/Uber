package com.uber.uber.repository;

import com.uber.uber.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepo extends JpaRepository <CarModel, Integer> {
}
