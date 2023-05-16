package com.uber.uber.repository;

import com.uber.uber.models.RiderWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderWalletRepo extends JpaRepository <RiderWallet, Integer> {

    RiderWallet findByRiderId(int riderId);
}
