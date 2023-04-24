package com.uber.uber.controllers;

import com.uber.uber.models.Status;
import com.uber.uber.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class StatusController {
    @Autowired
    private StatusService service;


    @GetMapping("/status")
    public List<Status> getStatus() {
        return service.getStatuss();
    }

}
