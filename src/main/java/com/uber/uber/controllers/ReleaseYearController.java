package com.uber.uber.controllers;

import com.uber.uber.models.ReleaseYear;
import com.uber.uber.service.ReleaseYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(
        maxAge = 3600
)
@RestController
public class ReleaseYearController {
    @Autowired
    private ReleaseYearService service;


    @GetMapping("/release_years")
    public List<ReleaseYear> getReleaseYears() {
        return service.getReleaseYears();
    }
}