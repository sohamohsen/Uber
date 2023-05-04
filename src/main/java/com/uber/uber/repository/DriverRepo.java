package com.uber.uber.repository;

import com.uber.uber.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver, Integer> {

    Driver findByAccountId(int accountId);
    Driver findByPhoneNumber(String phoneNumber);

    Driver findByNationalId(long nationalId);

    Driver findByDriverLicence(long driverLicence);
    List<Driver> findByCityId(int cityId);
}
