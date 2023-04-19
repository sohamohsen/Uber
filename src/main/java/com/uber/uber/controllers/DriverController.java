package com.uber.uber.controllers;

import com.uber.uber.models.Driver;
import com.uber.uber.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(
        maxAge = 3600
)
@RestController
public class DriverController {
    @Autowired
    private DriverService service;

    @GetMapping("/drivers")
    public List<Driver> getDrivers() {
        return service.getDrivers();
    }

}
