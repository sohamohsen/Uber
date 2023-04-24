package com.uber.uber.service;

import com.uber.uber.models.Account;
import com.uber.uber.models.RiderWallet;
import com.uber.uber.repository.AccountRepo;
import com.uber.uber.repository.RiderWalletRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RiderWalletService {
    @Autowired
    private RiderWalletRepo repo;

    public List<RiderWallet> getRiderWallets(){
        return repo.findAll();
    }

    public RiderWallet getRiderWalletById(int id){
        return repo.findById(id).get();
    }

    public RiderWallet save(RiderWallet riderwallet){
        return repo.save(riderwallet);
    }
}
