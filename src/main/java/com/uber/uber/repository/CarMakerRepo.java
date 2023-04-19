package com.uber.uber.repository;

import com.uber.uber.models.CarMaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarMakerRepo extends JpaRepository<CarMaker, Integer> {
}
