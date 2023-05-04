package com.uber.uber.service;

import com.uber.uber.models.Status;
import com.uber.uber.repository.StatusRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StatusService {
    @Autowired
    private StatusRepo repo;

    public List<Status> getStatus(){
        return repo.findAll();
    }

    public Status getStatusById(int id){
        return repo.findById(id).get();
    }

    public void save(Status status){
        repo.save(status);
    }
}
