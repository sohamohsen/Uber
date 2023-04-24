package com.uber.uber.controllers;

import com.google.gson.Gson;
import com.uber.uber.models.Account;
import com.uber.uber.models.Rider;
import com.uber.uber.service.AccountService;
import com.uber.uber.service.RiderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(
        maxAge = 3600
)
@RestController
public class RiderController {

    @Autowired
    private RiderService service;

    @Autowired
    private AccountService accountService;

    @GetMapping("/riders")
    public List<Rider> getRiders() {
        return service.getRiders();
    }



    @RequestMapping(
            path = "/rider", // path after base url http://localhost:8080/sign_up
            method = RequestMethod.POST, // request type
            produces = MediaType.APPLICATION_JSON_VALUE, // request data type
            consumes = MediaType.APPLICATION_JSON_VALUE // response data type
    )
    public ResponseEntity<Object> signUp(@RequestBody Rider payLoad){
        // check if user exist in account table
        // check if account is type rider
        // check if user exists in database
        Rider riderFromDb = service.getRiderByUserId(payLoad.accountId);
        Account account = accountService.getAccountById(payLoad.accountId);

        if (riderFromDb != null){
            // return error
            JSONObject object = new JSONObject();
            object.put("error","You already Created Profile");
            return new ResponseEntity<>(object.toString(), HttpStatus.FORBIDDEN);
        }else {
            Rider rider = new Rider();
            rider.accountId = payLoad.accountId;
            rider.cityId = payLoad.cityId;
            rider.firstName = payLoad.firstName;
            rider.lastName = payLoad.lastName;
            rider.phoneNumber = payLoad.phoneNumber;
            rider.birthdate = payLoad.birthdate;
            return new ResponseEntity<>(service.save(rider), HttpStatus.CREATED);
            //create new account
            // check for validation value
           /* Account account = new Account();
            JSONObject object = new JSONObject();
            JSONObject error = new JSONObject();
            String email = payLoad.getEmail();
            String password = payLoad.getPassword();
            String type = payLoad.getType();
            boolean isEmailInvalid = email.isBlank() || !email.contains("@") || !email.contains(".com");
            boolean isPasswordInValid = password.isBlank() || password.length() < 8;
            boolean isTypeInvalid = !type.equals("rider") && !type.equals("driver");
            if (isEmailInvalid) {
                error.put("email","Invalid Email");
            }
            if (isPasswordInValid) {
                error.put("password","Invalid Password");
            }
            if (isTypeInvalid){
                error.put("type","Invalid Type");
            }

            if (isEmailInvalid || isPasswordInValid || isTypeInvalid){
                object.put("error",error); // wrap
                return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
            }else {
                // create new account and return account
                account.setEmail(email);
                account.setPassword(password);
                account.setType(type);
                return new ResponseEntity<>(service.save(account),HttpStatus.CREATED);
            }*/

        }
    }








}
