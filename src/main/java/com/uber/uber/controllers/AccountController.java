package com.uber.uber.controllers;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.uber.uber.models.Account;
import com.uber.uber.service.AccountService;
import jakarta.websocket.server.PathParam;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@CrossOrigin(
        maxAge = 3600
)
@RestController
public class AccountController {


    @Autowired
    private AccountService service;


    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return service.getAccounts();
    }


    @GetMapping("/accounts/{user_id}")
    public Account getAccount(
            @PathVariable("user_id") int id
    ){
        return service.getAccountById(id);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody Account account){
        //Logic Layer validation layer
        service.save(account);
    }

    @RequestMapping(
            path = "/sign_in", // path after base url http://localhost:8080/sign_in
            method = RequestMethod.POST, // request type
            produces = MediaType.APPLICATION_JSON_VALUE, // request data type
            consumes = MediaType.APPLICATION_JSON_VALUE // response data type
    )
    public ResponseEntity<Object> signIn(@RequestBody Account payLoad){
        //1. check if email exists in database
        Account account = service.getAccountByEmail(payLoad.getEmail());
        if (account != null) { // if exist
            //2. check if password matches
            if (account.getPassword().equals(payLoad.getPassword())) {
                // sign in success
                //3. return account
                return new ResponseEntity<>(account, HttpStatus.OK);
            }else {
                // if password wrong
                // OR 3. return wrong password message
                JSONObject object = new JSONObject();
                object.put("error","Wrong password.");
                return new ResponseEntity<>(object.toString(), HttpStatus.UNAUTHORIZED);
            }
        }else {
            // if account doesn't exist
            // OR 3. return wrong account not found
            JSONObject object = new JSONObject();
            object.put("error","User doesn't exist.");
            return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
        }

    }
}
