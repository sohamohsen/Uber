package com.uber.uber.controllers;


import com.uber.uber.models.CarMaker;
import com.uber.uber.service.CarMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class CarMakerController {
    @Autowired
    private CarMakerService service;


    @GetMapping("/carmakers")
    public List<CarMaker> getCarMakers() {
        return service.getCarMakers();
    }


}
