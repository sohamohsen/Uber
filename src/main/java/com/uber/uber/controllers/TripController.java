package com.uber.uber.controllers;

import com.google.gson.Gson;
import com.uber.uber.form.TripForm;
import com.uber.uber.models.*;
import com.uber.uber.service.*;
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
public class TripController {
    @Autowired
    private TripService service;
    @Autowired
    private DriverService driverService;
    @Autowired
    private RiderService riderService;
    @Autowired
    private StatusService statusService;




    @GetMapping("/trips")
    public List<Trip> getTrips() {
        return service.getTrips();
    }

    //TODO requirements of trip creation
    /**
     * rider id
     * driver id
     *
     *
     */
    @RequestMapping(
            path = "/trip",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> createTrip(
            @RequestBody TripForm payload
    ){
        Rider rider = null;
        try {
            rider = riderService.getRiderByUserId(payload.accountId);
        }catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        if (rider != null) {
            //TODO do this later , we need to check if rider or driver do not have an opened trip

            //check if rider does not have a trip
            List<Driver> drivers = driverService.getDriverByCityId(rider.cityId);

            // maybe we have a drivers but they are not available
            if (!drivers.isEmpty()) {

                Driver driver = null; //this is our driver that we will join him the trip
                for (Driver d : drivers) {
                    if (d.available) {
                        //check if driver does not have a trip
                        driver = d;

                        break;
                    }
                }
                //here add this condition
                if (driver == null){
                    JSONObject object = new JSONObject();
                    object.put("error", "There are no available drivers right now.");
                    return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
                }else {
                    //create a trip
                    Trip trip = new Trip(
                            payload.pickLocLat,
                            payload.pickLocLng,
                            payload.dropLocLng,
                            payload.dropLocLat,
                            4,
                            driver.id,
                            rider.id
                    );
                    //complete

                    return new ResponseEntity<>(service.save(trip), HttpStatus.CREATED);
                }

            }else{
                JSONObject object = new JSONObject();
                object.put("error", "There are no available drivers in your city.");
                return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
            }
        }else{
            JSONObject object = new JSONObject();
            object.put("error", "Something wrong.");
            return new ResponseEntity<>(object.toString(),HttpStatus.FORBIDDEN);
        }

    }

}



