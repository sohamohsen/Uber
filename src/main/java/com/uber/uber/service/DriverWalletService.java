package com.uber.uber.service;

import com.uber.uber.models.Driver;
import com.uber.uber.models.DriverWallet;
import com.uber.uber.repository.DriverRepo;
import com.uber.uber.repository.DriverWalletRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DriverWalletService {
    @Autowired
    private DriverWalletRepo repo;

    public List<DriverWallet> getDriverWallets(){
        return repo.findAll();
    }
}
