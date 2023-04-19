package com.uber.uber.service;

import com.uber.uber.models.ReleaseYear;
import com.uber.uber.repository.ReleaseYearRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReleaseYearService {
    @Autowired
    private ReleaseYearRepo repo;

    public List<ReleaseYear> getReleaseYears(){
        return repo.findAll();
    }

}
