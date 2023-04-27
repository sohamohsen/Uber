package com.uber.uber.controllers;

import com.uber.uber.models.City;
import com.uber.uber.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(
        maxAge = 3600
)
@RestController
public class CityController {
    @Autowired
    private CityService service;


    //Request to get all cities
    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities() {
        return new ResponseEntity<>(service.getCities(), HttpStatus.OK);
    }
}
