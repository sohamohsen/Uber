package com.uber.uber.controllers;

import com.uber.uber.models.Payment;
import com.uber.uber.models.Vehicle;
import com.uber.uber.service.PaymentService;
import com.uber.uber.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class PaymentController {
    @Autowired
    private PaymentService service;


    @GetMapping("/payments")
    public List<Payment> getPayments() {
        return service.getPayments();
    }

}
