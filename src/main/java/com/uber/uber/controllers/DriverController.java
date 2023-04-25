package com.uber.uber.controllers;

import com.google.gson.Gson;
import com.uber.uber.models.Account;
import com.uber.uber.models.Driver;
import com.uber.uber.models.DriverWallet;
import com.uber.uber.service.AccountService;
import com.uber.uber.service.DriverService;
import com.uber.uber.service.DriverWalletService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class DriverController {

    @Autowired
    private DriverService service;

    @Autowired
    private AccountService accountService;
    @Autowired
    private DriverWalletService driverWalletService;

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getDrivers() {
        return new ResponseEntity<>(service.getDrivers(), HttpStatus.OK);
    }


    @RequestMapping(
            path = "/driver",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createNewDriver(
            @RequestBody Driver payload
    ){
        Account account = null;
        try {
            account = accountService.getAccountById(payload.accountId);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        if (account != null && account.type.equals("driver")){
            Driver driverFromDb = service.getDriverByAccountId(account.id);
            if (driverFromDb != null){
                JSONObject object = new JSONObject();
                object.put("error","User already exist");
                return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
            }else {
                if (service.getDriverByPhoneNumber(payload.phoneNumber) != null){
                    JSONObject object = new JSONObject();
                    object.put("error","Phone Number is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }else if (service.getDriverByNationalId(payload.nationalId) != null){
                    JSONObject object = new JSONObject();
                    object.put("error", "Maybe your National id is wrong or is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }else if (service.getDriverByDriverLicence(payload.driverLicence) != null){
                    JSONObject object = new JSONObject();
                    object.put("error", "Maybe your Driver Licence is wrong or is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }
                // success creating driver create a wallet
                Driver newdriver = null;
                try {
                    newdriver = service.save(payload);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if (newdriver != null){
                    DriverWallet wallet = new DriverWallet(newdriver.id,0.0f);
                    driverWalletService.save(wallet);
                    return new ResponseEntity<>(newdriver,HttpStatus.CREATED);
                }else{
                    JSONObject object = new JSONObject();
                    object.put("error","Something went wrong.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }
            }
        }else {
             JSONObject object = new JSONObject();
            object.put("error","User does not have account.");
            return new ResponseEntity<>(object.toString(),HttpStatus.NOT_FOUND);
        }
    }

}
