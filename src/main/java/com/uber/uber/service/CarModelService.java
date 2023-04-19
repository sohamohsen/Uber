package com.uber.uber.service;

import com.uber.uber.models.CarModel;
import com.uber.uber.repository.CarModelRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CarModelService {
    @Autowired
    private CarModelRepo repo;

    public List<CarModel> getCarModels(){
        return repo.findAll();
    }

    public CarModel getCarModelById(int id){
        return repo.findById(id).get();
    }



    public void save(CarModel carmodel){
        repo.save(carmodel);
    }
}
