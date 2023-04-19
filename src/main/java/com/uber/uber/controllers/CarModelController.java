package com.uber.uber.controllers;

import com.uber.uber.models.CarModel;
import com.uber.uber.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class CarModelController {
    @Autowired
    private CarModelService service;


    @GetMapping("/carmodels")
    public List<CarModel> getCarModels() {
        return service.getCarModels();
    }
}
