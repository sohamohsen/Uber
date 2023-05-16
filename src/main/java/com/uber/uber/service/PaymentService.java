package com.uber.uber.service;

import com.uber.uber.models.Payment;
import com.uber.uber.repository.PaymentRepo;
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
    public Payment getPaymentByTrip(int tripId){
        return repo.findPaymentByTripId(tripId);
    }
    public Payment getPaymentByRiderId(int riderId){
        return repo.findPaymentByRiderId(riderId);
    }

    public Payment save(Payment payment){
        return repo.save(payment);
    }

}
