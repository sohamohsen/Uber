package com.uber.uber.service;

import com.uber.uber.models.CarMaker;
import com.uber.uber.repository.CarMakerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CarMakerService {
    @Autowired
    private CarMakerRepo repo;

    public List<CarMaker> getCarMakers(){
        return repo.findAll();
    }

    public CarMaker getCarMakerById(int id){

        return repo.findById(id).get();
    }



    public void save(CarMaker carmaker){

        repo.save(carmaker);
    }
}
