package com.uber.uber.controllers;

import com.uber.uber.models.ReleaseYear;
import com.uber.uber.service.ReleaseYearService;
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
public class ReleaseYearController {
    @Autowired
    private ReleaseYearService service;

    // Return list with all released years
    @GetMapping("/release_years") // path after base url http://localhost:8080/release_years
    public ResponseEntity<List<ReleaseYear>> getReleaseYears() {
        return new ResponseEntity<> (service.getReleaseYears(), HttpStatus.OK);
    }
}
