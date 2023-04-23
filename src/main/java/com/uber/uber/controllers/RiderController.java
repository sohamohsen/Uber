package com.uber.uber.controllers;

import com.uber.uber.models.Account;
import com.uber.uber.models.Rider;
import com.uber.uber.service.AccountService;
import com.uber.uber.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(
        maxAge = 3600
)
@RestController
public class RiderController {

    @Autowired
    private RiderService service;

    @GetMapping("/riders")
    public List<Rider> getRiders() {
        return service.getRiders();
    }




}
