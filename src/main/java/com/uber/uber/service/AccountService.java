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
        return repo.findByEmailIs(email);
    }

    public Account save(Account account){
        return repo.save(account);
    }

}