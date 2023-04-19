package com.uber.uber.service;

import com.uber.uber.models.Account;
import com.uber.uber.models.Color;
import com.uber.uber.repository.AccountRepo;
import com.uber.uber.repository.ColorRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ColorService {
        @Autowired
        private ColorRepo repo;

        public List<Color> getColors(){
            return repo.findAll();
        }
}
