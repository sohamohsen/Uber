package com.uber.uber.controllers;

import com.uber.uber.models.Account;
import com.uber.uber.models.Rider;
import com.uber.uber.models.RiderWallet;
import com.uber.uber.service.AccountService;
import com.uber.uber.service.RiderService;
import com.uber.uber.service.RiderWalletService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class RiderController {

    @Autowired
    private RiderService service;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RiderWalletService riderWalletService;

    //Request get list with all riders
    @GetMapping("/riders")
    public List<Rider> getRiders() {

        return service.getRiders();
    }

    @PostMapping(
            path = "/rider",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createRiderProfile(@RequestBody Rider payload) {

        // 1️⃣ Validate account existence and type
        Account account = accountService.getAccountById(payload.getAccount().getId());
        if (account == null || account.getType() != Account.AccountType.RIDER) {
            return errorResponse("User does not have a valid rider account.", HttpStatus.NOT_FOUND);
        }

        // 2️⃣ Ensure rider profile doesn't already exist
        if (service.getRiderByUserId(account.getId()) != null) {
            return errorResponse("You already created a profile.", HttpStatus.FORBIDDEN);
        }

        // 3️⃣ Ensure phone number is unique
        if (service.getRiderByPhoneNumber(payload.getPhoneNumber()) != null) {
            return errorResponse("Phone number is already in use.", HttpStatus.FORBIDDEN);
        }

        // 4️⃣ Save new rider
        try {
            Rider newRider = service.save(payload);

            // 5️⃣ Create wallet
            RiderWallet wallet = new RiderWallet(0.0f, newRider.getId());
            riderWalletService.save(wallet);

            return new ResponseEntity<>(newRider, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return errorResponse("Something went wrong while creating the rider profile.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Utility method to generate error JSON responses
     */
    private ResponseEntity<?> errorResponse(String message, HttpStatus status) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return new ResponseEntity<>(error, status);
    }


}
