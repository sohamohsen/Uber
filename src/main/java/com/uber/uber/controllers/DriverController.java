package com.uber.uber.controllers;

import com.uber.uber.models.Account;
import com.uber.uber.models.Driver;
import com.uber.uber.models.DriverWallet;
import com.uber.uber.service.AccountService;
import com.uber.uber.service.DriverService;
import com.uber.uber.service.DriverWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(maxAge = 3600)
@RestController
public class DriverController {

    @Autowired
    private DriverService service;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DriverWalletService driverWalletService;

    @GetMapping("/availability") // Example: /availability?account_id=47&availability=true
    public ResponseEntity<?> changeDriverAvailability(
            @RequestParam(name = "account_id", defaultValue = "0") int accountId,
            @RequestParam(name = "availability", defaultValue = "true") boolean available
    ) {
        Driver driver = service.getDriverByAccountId(accountId);
        if (driver == null) {
            return errorResponse("Driver not found.", HttpStatus.NOT_FOUND);
        }

        driver.setAvailable(available);
        service.save(driver);

        // Update account online status as well
        Account account = driver.getAccount();
        account.setOnline(available);
        accountService.save(account);

        return ResponseEntity.ok(driver);
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getDrivers() {
        return ResponseEntity.ok(service.getDrivers());
    }

    @PostMapping(
            path = "/driver",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createNewDriver(@RequestBody Driver payload) {

        // 1️⃣ Validate account existence & type
        Account account = accountService.getAccountById(payload.getAccount().getId());
        if (account == null || account.getType() != Account.AccountType.DRIVER) {
            return errorResponse("User does not have a valid driver account.", HttpStatus.NOT_FOUND);
        }

        // 2️⃣ Check if driver profile already exists
        if (service.getDriverByAccountId(account.getId()) != null) {
            return errorResponse("You already created a profile.", HttpStatus.FORBIDDEN);
        }

        // 3️⃣ Validate phone, national ID, and license uniqueness
        if (service.getDriverByPhoneNumber(payload.getPhoneNumber()) != null) {
            return errorResponse("Phone number is already in use.", HttpStatus.FORBIDDEN);
        }
        if (service.getDriverByNationalId(payload.getNationalId()) != null) {
            return errorResponse("National ID is already in use or invalid.", HttpStatus.FORBIDDEN);
        }
        if (service.getDriverByDriverLicence(Long.parseLong(payload.getDriverLicence())) != null) {
            return errorResponse("Driver licence is already in use or invalid.", HttpStatus.FORBIDDEN);
        }

        // 4️⃣ Save driver profile
        try {
            Driver newDriver = service.save(payload);

            // 5️⃣ Create wallet with 500 EGP base balance
            DriverWallet wallet = new DriverWallet(newDriver.getId(), 500.0f);
            driverWalletService.save(wallet);

            // Mark account online
            account.setOnline(true);
            accountService.save(account);

            return new ResponseEntity<>(newDriver, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return errorResponse("Something went wrong while creating the driver profile.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 📌 Utility method for consistent error responses
    private ResponseEntity<Map<String, String>> errorResponse(String message, HttpStatus status) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return new ResponseEntity<>(error, status);
    }
}
