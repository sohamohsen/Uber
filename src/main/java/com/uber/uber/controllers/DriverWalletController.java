package com.uber.uber.controllers;

import com.uber.uber.models.DriverWallet;
import com.uber.uber.service.DriverWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class DriverWalletController {
    @Autowired
    private DriverWalletService service;

    // Request a list with all driver's wallets
    @GetMapping("/driverwallets") // path after base url http://localhost:8080/driverwallet
    public List<DriverWallet> getDriverWallets() {
        return service.getDriverWallets();
    }

}
