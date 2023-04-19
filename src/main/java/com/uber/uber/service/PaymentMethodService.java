package com.uber.uber.service;

import com.uber.uber.models.PaymentMethod;
import com.uber.uber.models.Vehicle;
import com.uber.uber.repository.PaymentMethodRepo;
import com.uber.uber.repository.VehicleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepo repo;

    public List<PaymentMethod> getPaymentMethods(){
        return repo.findAll();
    }

    public PaymentMethod getPaymentMethodById(int id){
        return repo.findById(id).get();
    }

    public void save(PaymentMethod paymentmethod){
        repo.save(paymentmethod);
    }

}
