package com.uber.uber.controllers;

import com.uber.uber.models.Transaction;
import com.uber.uber.models.Vehicle;
import com.uber.uber.service.TransactionService;
import com.uber.uber.service.VehicleService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(
        maxAge = 3600
)
@RestController
public class TransactionController {
    @Autowired
    private TransactionService service;


    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return service.getTransactions();
    }

}
