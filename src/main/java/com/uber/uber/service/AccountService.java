package com.uber.uber.service;

import com.uber.uber.models.Account;
import com.uber.uber.repository.AccountRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepo repo;

    //method to return all accounts
    public List<Account> getAccounts(){
        return repo.findAll();
    }

    //return each account by id
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Account getAccountById(int id) throws NoSuchElementException {
        Account account = null;
        try {
            account= repo.findById(id).get();
        }catch (NoSuchElementException e){
           e.printStackTrace();
        }
        return account;
    }

    //return each account by id
    public Account getAccountByEmail(String email){
        return repo.findByEmailIs(email);
    }

    public Account save(Account account){
        return repo.save(account);
    }

}
