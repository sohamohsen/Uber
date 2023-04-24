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

    @Autowired
    private RiderWalletService riderWalletService;

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

        Account account = accountService.getAccountById(payLoad.accountId);

        if (account != null && account.type.equals("rider")){
            Rider riderFromDb = service.getRiderByUserId(payLoad.accountId);
            if (riderFromDb != null){
                // return error
                JSONObject object = new JSONObject();
                object.put("error","You already Created Profile");
                return new ResponseEntity<>(object.toString(), HttpStatus.FORBIDDEN);
            }else {
                if (service.getRiderByPhoneNumber(payLoad.phoneNumber) != null){
                    JSONObject object = new JSONObject();
                    object.put("error","Phone Number is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }
                // on success creating rider create a wallet
                Rider newRider = null;
                try{
                    newRider = service.save(payLoad);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if (newRider != null){
                    //create wallet here
                    RiderWallet wallet = new RiderWallet(0.0f, newRider.id);
                    RiderWallet newWallet = riderWalletService.save(wallet);

                    return new ResponseEntity<>(newRider,HttpStatus.CREATED);
                }else {
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
