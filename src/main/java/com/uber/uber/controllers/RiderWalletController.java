package com.uber.uber.controllers;

import com.uber.uber.models.Account;
import com.uber.uber.models.RiderWallet;
import com.uber.uber.service.AccountService;
import com.uber.uber.service.RiderWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(
        maxAge = 3600
)
@RestController
public class RiderWalletController {
    @Autowired
    private RiderWalletService service;


    @GetMapping("/rider_wallets")
    public List<RiderWallet> getRiderWallets() {
        return service.getRiderWallets();
    }
}
