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

    //Request get list with all riders
    @GetMapping("/riders")
    public List<Rider> getRiders() {

        return service.getRiders();
    }

    //Request to make new rider profile
    @RequestMapping(
            path = "/rider", // path after base url http://localhost:8080/sign_up
            method = RequestMethod.POST, // request type
            produces = MediaType.APPLICATION_JSON_VALUE, // request data type
            consumes = MediaType.APPLICATION_JSON_VALUE // response data type
    )
    public ResponseEntity<Object> signUp(@RequestBody Rider payLoad){ //TODO change the function name to createRiderProfile

        // 1. Check if the rider have account or not
        Account account = accountService.getAccountById(payLoad.accountId);
        if (account != null && account.type.equals("rider")){

            // 2. Check if rider already have profile or not.
            Rider riderFromDb = service.getRiderByUserId(payLoad.accountId); // TODO why riderFrom used
            if (riderFromDb != null){
                // 3. if he/she already hav account return error
                JSONObject object = new JSONObject();
                object.put("error","You already Created Profile");
                return new ResponseEntity<>(object.toString(), HttpStatus.FORBIDDEN);
            }else {
                // or 3. Check validation
                // 3.1. Check that phone number is unique.
                if (service.getRiderByPhoneNumber(payLoad.phoneNumber) != null){
                    JSONObject object = new JSONObject();
                    object.put("error","Phone Number is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }
                // 4. Create new rider profile
                Rider newRider = null;
                try{
                    newRider = service.save(payLoad);
                }catch (Exception e){
                    e.printStackTrace();
                }
                // 5. Create wallet for rider
                if (newRider != null){
                    //create wallet here
                    RiderWallet wallet = new RiderWallet(0.0f, newRider.id);
                    RiderWallet newWallet = riderWalletService.save(wallet);
                    return new ResponseEntity<>(newRider,HttpStatus.CREATED);
                }else {
                    // or 5. Return error
                    JSONObject object = new JSONObject();
                    object.put("error","Something went wrong.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }
            }
        }else {
            // or 2. return error
            JSONObject object = new JSONObject();
            object.put("error","User does not have account.");
            return new ResponseEntity<>(object.toString(),HttpStatus.NOT_FOUND);
        }
    }

}
