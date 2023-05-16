package com.uber.uber.repository;

import com.uber.uber.models.DriverWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverWalletRepo extends JpaRepository<DriverWallet, Integer> {

    DriverWallet findByDriverId(int driverId);
}
