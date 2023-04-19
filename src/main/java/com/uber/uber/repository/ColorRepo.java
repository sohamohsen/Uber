package com.uber.uber.repository;

import com.uber.uber.models.Account;
import com.uber.uber.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ColorRepo extends JpaRepository<Color,Integer> {
}
