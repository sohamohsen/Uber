package com.uber.uber.service;

import com.uber.uber.models.Account;
import com.uber.uber.models.Color;
import com.uber.uber.models.Driver;
import com.uber.uber.models.Rider;
import com.uber.uber.repository.ColorRepo;
import com.uber.uber.repository.DriverRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class DriverService {
    @Autowired
    private DriverRepo repo;

    public List<Driver> getDrivers(){
        return repo.findAll();
    }


    public Driver getDriverByAccountId(int accountId){
        return repo.findByAccountId(accountId);
    }

    public Driver save(Driver driver){
        return repo.save(driver);
    }

    public Driver getDriverByPhoneNumber(String phoneNumber){
       return repo.findByPhoneNumber(phoneNumber);

    }
    public Driver getDriverByNationalId(long nationalId){
        return repo.findByNationalId(nationalId);
    }
    public Driver getDriverByDriverLicence(long driverLicence){
        return repo.findByDriverLicence(driverLicence);
    }

}
