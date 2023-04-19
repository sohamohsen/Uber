package com.uber.uber.controllers;


import com.uber.uber.models.Account;
import com.uber.uber.service.AccountService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping("/sign_in")
    public Account signIn(@RequestBody Account payLoad){
        Account account = service.getAccountByEmail(payLoad.getEmail());
        if (account.getPassword().equals(payLoad.getPassword())){
            return account;
        }
        return null;
    }
}
