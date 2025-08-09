package com.uber.uber.controllers;

import com.google.gson.Gson;
import com.uber.uber.form.VehicleForm;
import com.uber.uber.models.Driver;
import com.uber.uber.models.Vehicle;
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

    // Request a list of all vehicles
    @GetMapping("/vehicles") // path after base url http://localhost:8080/vehicles
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return new ResponseEntity<>(service.getVehicles(), HttpStatus.OK);
    }

    // Request to create vehicle account
    @RequestMapping(
            path = "/vehicle", // path after base url http://localhost:8080/vehicle
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, // request data type
            consumes = MediaType.APPLICATION_JSON_VALUE // request data type
    )

    public ResponseEntity<Object> createVehicle(
            @RequestBody VehicleForm payload
    ){
        // 1. Check if the driver has account.
        Driver driver = null;
        try {
            driver = driverService.getDriverByAccountId(payload.accountId);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        // 2. Check that driver dosen't have another vehicle.
        if (driver != null){
            Vehicle vehicleFromDb = service.getVehicleByDriverId(driver.getId());
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
                        driver.getId()
                );
                // 3. Check validation
                if(service.getVehicleByVehicleLicence(payload.vehicleLicence) != null) {
                    JSONObject object = new JSONObject();
                    object.put("error", "Maybe your Vehicle Licence is wrong or is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }else if (service.getVehicleByLicencePlate(payload.licencePlate) != null){
                    JSONObject object = new JSONObject();
                    object.put("error", "Maybe your Licence Plate is wrong or is already in use.");
                    return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
                }
                Gson gson = new Gson(); // create json file
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



