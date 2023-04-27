package com.uber.uber.service;

import com.uber.uber.models.City;
import com.uber.uber.repository.CityRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CityService {
    @Autowired
    private CityRepo repo;

    //method to return all cites
    public List<City> getCities(){
        return repo.findAll();
    }


    public City getAccountById(int id){

        return repo.findById(id).get();
    }
}
