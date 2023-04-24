package com.uber.uber.repository;

import com.uber.uber.models.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepo extends JpaRepository<Rider,Integer> {


    Rider findByAccountId(int accountId);
    Rider findByPhoneNumber(String phoneNumber);
}
