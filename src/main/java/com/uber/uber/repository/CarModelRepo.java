package com.uber.uber.repository;

import com.uber.uber.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarModelRepo extends JpaRepository <CarModel, Integer> {

    List<CarModel> findByCarMakerId(Integer carMakerId);


}
