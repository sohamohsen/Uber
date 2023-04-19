package com.uber.uber.service;

import com.uber.uber.models.Payment;
import com.uber.uber.models.PaymentMethod;
import com.uber.uber.models.Vehicle;
import com.uber.uber.repository.PaymentMethodRepo;
import com.uber.uber.repository.PaymentRepo;
import com.uber.uber.repository.VehicleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class PaymentService {
    @Autowired
    private PaymentRepo repo;

    public List<Payment> getPayments(){
        return repo.findAll();
    }

    public Payment getPaymentById(int id){
        return repo.findById(id).get();
    }

    public void save(Payment payment){
        repo.save(payment);
    }

}
