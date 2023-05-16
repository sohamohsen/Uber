package com.uber.uber.controllers;

import com.uber.uber.models.CarModel;
import com.uber.uber.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class CarModelController {
    @Autowired
    private CarModelService service;

    // Return list with each car maker models
    @GetMapping("car_model/{car_maker_id}") // path after base url http://localhost:8080/car_model/(car_maker_id)
    public ResponseEntity<List<CarModel>> getCarModelsByCarMakerId(
            @PathVariable(name = "car_maker_id") int carMakerId){
      return new ResponseEntity<>(service.getCarModelsByCarMakerId(carMakerId),HttpStatus.OK);
    }
}
