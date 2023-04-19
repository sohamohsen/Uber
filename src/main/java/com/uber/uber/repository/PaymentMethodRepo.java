package com.uber.uber.repository;

import com.uber.uber.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepo extends JpaRepository <PaymentMethod, Integer> {
}
