package com.uber.uber.service;

import com.uber.uber.models.Rider;
import com.uber.uber.repository.RiderRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RiderService {

    @Autowired
    private RiderRepo repo;

    public List<Rider> getRiders(){
        return repo.findAll();
    }


    public Rider getRiderByPhoneNumber(String phoneNumber){
        return repo.findByPhoneNumber(phoneNumber);
    }

    public Rider getRiderByUserId(int accountId){
        return repo.findByAccountId(accountId);
    }

    public Rider save(Rider rider){
        return repo.save(rider);
    }


}
