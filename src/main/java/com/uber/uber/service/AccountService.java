package com.uber.uber.service;

import com.uber.uber.models.Account;
import com.uber.uber.repository.AccountRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepo repo;

    public List<Account> getAccounts(){
        return repo.findAll();
    }

    public Account getAccountById(int id){
        return repo.findById(id).get();
    }

    public Account getAccountByEmail(String email){
        //TODO modify query , where email == account.email
        return repo.findById(1).get();
    }

    public void save(Account account){
        repo.save(account);
    }

}
