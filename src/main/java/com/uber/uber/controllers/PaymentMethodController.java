package com.uber.uber.controllers;

import com.uber.uber.models.PaymentMethod;
import com.uber.uber.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService service;


    @GetMapping("/paymentmethods")
    public List<PaymentMethod> getPaymentMethods() {
        return service.getPaymentMethods();
    }

}