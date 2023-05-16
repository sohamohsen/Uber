package com.uber.uber.controllers;

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


    // Request to change the availability of driver in case of log out.
    @GetMapping("/availability") // log_out?driverId=47&availability=true
    public ResponseEntity<Object> changeDriverAvailability(
            @RequestParam(name = "account_id",defaultValue = "0") int accountId,
            @RequestParam(name = "availability", defaultValue = "true") boolean available
    ){
        Driver driver = service.getDriverByAccountId(accountId);
        if (driver != null){
            driver.available = available;
            service.save(driver);
        }
        return new ResponseEntity<>(driver,HttpStatus.OK);
    }

    // Request to get list of all driver
    @GetMapping("/drivers") // path after base url http://localhost:8080/drivers
    public ResponseEntity<List<Driver>> getDrivers() {
        return new ResponseEntity<>(service.getDrivers(), HttpStatus.OK);
    }

    // Request to create new driver profile
    @RequestMapping(
            path = "/driver", // path after base url http://localhost:8080/driver
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, // request data type
            consumes = MediaType.APPLICATION_JSON_VALUE // request data type
    )
    public ResponseEntity<Object> createNewDriver(
            @RequestBody Driver payload
    ){
        // 1. check if the driver has account or not
        Account account = null;
        try {
            account = accountService.getAccountById(payload.accountId);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        if (account != null && account.type.equals("driver")){
            // 2. check if driver already has profile or not
            Driver driverFromDb = service.getDriverByAccountId(account.id);
            if (driverFromDb != null){
                JSONObject object = new JSONObject();
                object.put("error","User already exist");
                return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
            }else {
                // 3. Check validation
                // 3.1. Check if the phone number unique
                if (service.getDriverByPhoneNumber(payload.phoneNumber) != null){
                    JSONObject object = new JSONObject();
                    object.put("error","Phone Number is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);

                    // 3.2. Check if the national id unique
                }else if (service.getDriverByNationalId(payload.nationalId) != null){
                    JSONObject object = new JSONObject();
                    object.put("error", "Maybe your National id is wrong or is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                    // 3.3. Check if the driver licence unique
                }else if (service.getDriverByDriverLicence(payload.driverLicence) != null){
                    JSONObject object = new JSONObject();
                    object.put("error", "Maybe your Driver Licence is wrong or is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }
                // 4. Create new profile
                Driver newdriver = null;
                try {
                    newdriver = service.save(payload);
                }catch (Exception e){
                    e.printStackTrace();
                }
                // 5. Create wallet for driver with base balance 500 EGP.
                if (newdriver != null){
                    DriverWallet wallet = new DriverWallet(newdriver.id,500.0f);
                    driverWalletService.save(wallet);
                    return new ResponseEntity<>(newdriver,HttpStatus.CREATED);
                }else{ // or 5. return error
                    JSONObject object = new JSONObject();
                    object.put("error","Something went wrong.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }
            }
        }else { // 2. return error
             JSONObject object = new JSONObject();
            object.put("error","User does not have account.");
            return new ResponseEntity<>(object.toString(),HttpStatus.NOT_FOUND);
        }
    }

}
