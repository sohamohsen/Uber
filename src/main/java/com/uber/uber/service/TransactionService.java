package com.uber.uber.service;

import com.uber.uber.models.Transaction;
import com.uber.uber.models.Vehicle;
import com.uber.uber.repository.TransactionRepo;
import com.uber.uber.repository.VehicleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepo repo;

    public List<Transaction> getTransactions(){
        return repo.findAll();
    }

    public Transaction getTransactionById(int id){
        return repo.findById(id).get();
    }

    public void save(Transaction transaction){
        repo.save(transaction);
    }

}
