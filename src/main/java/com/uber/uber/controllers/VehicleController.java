package com.uber.uber.controllers;

import com.google.gson.Gson;
import com.uber.uber.form.VehicleForm;
import com.uber.uber.models.Account;
import com.uber.uber.models.Driver;
import com.uber.uber.models.Vehicle;
import com.uber.uber.service.AccountService;
import com.uber.uber.service.DriverService;
import com.uber.uber.service.VehicleService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class VehicleController {
    @Autowired
    private VehicleService service;

    @Autowired
    private DriverService driverService;

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return new ResponseEntity<>(service.getVehicles(), HttpStatus.OK);
    }

    @RequestMapping(
            path = "/vehicle",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<Object> createNewDriver(
            @RequestBody VehicleForm payload
    ){
        Driver driver = null;
        try {
            driver = driverService.getDriverByAccountId(payload.accountId);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        if (driver != null){
            Vehicle vehicleFromDb = service.getVehicleByDriverId(driver.id);
            if (vehicleFromDb != null){
                JSONObject object = new JSONObject();
                object.put("error","User already exist");
                return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
            }else {
                Vehicle vehicle = new Vehicle(

                        payload.licencePlate,
                        payload.vehicleLicence,
                        payload.colorId,
                        payload.releaseYear,
                        payload.carModelId,
                        payload.carMakerId,
                        driver.id
                );
                if(service.getVehicleByVehicleLicence(payload.vehicleLicence) != null) {
                    JSONObject object = new JSONObject();
                    object.put("error", "Maybe your Vehicle Licence is wrong or is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }else if (service.getVehicleByLicencePlate(payload.licencePlate) != null){
                    JSONObject object = new JSONObject();
                    object.put("error", "Maybe your Licence Plate is wrong or is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }
                Gson gson = new Gson();
                System.out.println(gson.toJson(vehicle));
                return new ResponseEntity<>(service.save(vehicle),HttpStatus.CREATED);
            }
        }else {
            JSONObject object = new JSONObject();
            object.put("error","User does not have account.");
            return new ResponseEntity<>(object.toString(),HttpStatus.NOT_FOUND);
        }
    }
}



