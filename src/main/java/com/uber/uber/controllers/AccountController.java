package com.uber.uber.controllers;


import com.uber.uber.models.Account;
import com.uber.uber.service.AccountService;
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
public class AccountController {


    @Autowired
    private AccountService service;


    /* Request types
    post
    get
     */
    //end point or url path

    //List all accounts
    @GetMapping("/accounts") //http://localhost:8080/accounts
    public List<Account> getAccounts() {
        return service.getAccounts();
    }


    //Request to get each account by id
    @GetMapping("/accounts/{user_id}") // http://localhost:8080/account/2
    public ResponseEntity<Account> getAccount(
            @PathVariable("user_id") int id
    ){
        return new ResponseEntity<>(service.getAccountById(id),HttpStatus.OK);
    }

    //Request to get each account by email
    @GetMapping("/accounts/email/{email}")
    public Account getAccountByEmail(
            @PathVariable("email") String email
    ){
        return service.getAccountByEmail(email);
    }

    //Post request to sign up
    @RequestMapping(
            path = "/sign_up", // path after base url http://localhost:8080/sign_up
            method = RequestMethod.POST, // request type
            produces = MediaType.APPLICATION_JSON_VALUE, // request data type (Json)
            consumes = MediaType.APPLICATION_JSON_VALUE // response data type (Json)
    )
    public ResponseEntity<Object> signUp(
            @RequestBody Account payLoad
    ){
        //Validation
        //1. check if email exists in database
        Account accountFromDb = service.getAccountByEmail(payLoad.email);
        if (accountFromDb != null){
            // return error
            JSONObject object = new JSONObject();
            object.put("error","User already exist");
            return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
        }else {
            //create new account
            // check for validation value
            Account account = new Account();
            JSONObject object = new JSONObject();
            JSONObject error = new JSONObject();
            String email = payLoad.email;
            String password = payLoad.password;
            String type = payLoad.type;
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
                account.email = (email);
                account.password = (password);
                account.type = (type);
                return new ResponseEntity<>(service.save(account),HttpStatus.CREATED);
            }

        }
    }

    //Request for sign in
    @RequestMapping(
            path = "/sign_in", // path after base url http://localhost:8080/sign_in
            method = RequestMethod.POST, // request type
            produces = MediaType.APPLICATION_JSON_VALUE, // request data type
            consumes = MediaType.APPLICATION_JSON_VALUE // response data type
    )
    public ResponseEntity<Object> signIn(
            @RequestBody Account payLoad){
        //1. check if email exists in database
        Account account = service.getAccountByEmail(payLoad.email);
        if (account != null) { // if exist
            //2. check if password matches
            if (account.password.equals(payLoad.password)) {
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
