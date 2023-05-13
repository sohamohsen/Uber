package com.uber.uber.repository;

import com.uber.uber.models.Account;
import com.uber.uber.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

    Payment findPaymentByTripId(int tripId);   //instate of query
    Payment findPaymentByRiderId(int riderId);   //instate of query

}
