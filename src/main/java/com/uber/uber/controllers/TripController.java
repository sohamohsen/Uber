package com.uber.uber.controllers;

import com.uber.uber.form.TripForm;
import com.uber.uber.models.*;
import com.uber.uber.service.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

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

    // Requests to get lest with all trips
    @GetMapping("/trips") // path after base url http://localhost:8080/trips
    public List<Trip> getTrips() {
        return service.getTrips();
    }

    // Request to get each trip from it's id
    @GetMapping("/trip/{id}") // path after base url http://localhost:8080/trip/(id)
    public Trip getTripById(@PathVariable("id") int id) {
        return service.getTripById(id);
    }

    // Request to get requested trip
    @GetMapping("/new_request/{account_id}") // path after base url http://localhost:8080/new_reqest/(account_Id)
    public ResponseEntity<Object> getNewRequestedTrips(
            @PathVariable("account_id") int accountId
    ) { // 1. Check that the user order trip is already exist.
        Rider rider = riderService.getRiderByUserId(accountId);
        Driver driver = driverService.getDriverByAccountId(accountId);
        // 2. Return error if user not found
        if (rider == null && driver == null) {
            JSONObject object = new JSONObject();
            object.put("error", "Not Found");
            return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
            // or 2. rerun all requested user trips that status id 4
        } else if (rider != null) {
            return new ResponseEntity<>(service.getRiderTripsByStatusId(rider.id,4), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.getTripsByDriverIdAndStatusId(driver.id,4), HttpStatus.OK);
        }
    }

    // Request to get all canceled trips for specific USER
    @GetMapping("/all_canceled_trips/{account_id}") // path after base url http://localhost:8080/all_canceled_trips/(account_Id)
    public ResponseEntity<Object> getCanceledTrips(
            @PathVariable("account_id") int accountId
    ) { // 1. Check that the user order trip is already exist.
        Rider rider = riderService.getRiderByUserId(accountId);
        Driver driver = driverService.getDriverByAccountId(accountId);
        // 2. Return error if user not found
        if (rider == null && driver == null) {
            JSONObject object = new JSONObject();
            object.put("error", "Not Found");
            return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
            // or 2. rerun all canceled user trips that status id 1
        } else if (rider != null) {
            return new ResponseEntity<>(service.getRiderCanceledTrips(rider.id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.getCanceledTripByDriverId(driver.id), HttpStatus.OK);
        }
    }

    // Request to get all finished trips for specific USER
    @GetMapping("/all_finished_trips/{account_id}") // path after base url http://localhost:8080/all_finished_trips/(account_Id)
    public ResponseEntity<Object> getFinishedTrips(
            @PathVariable("account_id") int accountId
    ) { // 1. Check that the user order trip is already exist.
        Rider rider = riderService.getRiderByUserId(accountId);
        Driver driver = driverService.getDriverByAccountId(accountId);
        // 2. Return error if user not found
        if (rider == null && driver == null) {
            JSONObject object = new JSONObject();
            object.put("error", "Not Found");
            return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
            // or 2. rerun all finished user trips that status id 3
        } else if (rider != null) {
            return new ResponseEntity<>(service.getRiderFinishedTrips(rider.id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.getFinishedTripByDriverId(driver.id), HttpStatus.OK);
        }
    }


    // Request to get all started trips for specific USER
    @GetMapping("/all_started_trips/{account_id}") // path after base url http://localhost:8080/all_started_trips/(account_Id)
    public ResponseEntity<Object> getStartedTrips(
            @PathVariable("account_id") int accountId
    ) { // 1. Check that the user order trip is already exist.
        Rider rider = riderService.getRiderByUserId(accountId);
        Driver driver = driverService.getDriverByAccountId(accountId);
        // 2. Return error if user not found
        if (rider == null && driver == null) {
            JSONObject object = new JSONObject();
            object.put("error", "Not Found");
            return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
            // or 2. return all started user trips that status id 2
        } else if (rider != null) {
            return new ResponseEntity<>(service.getRiderStartedTrips(rider.id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.getStartedTripByDriverId(driver.id), HttpStatus.OK);
        }
    }


    // Request to get all trips for specific user
    @GetMapping("/trips/{account_id}") // path after base url http://localhost:8080/trips/(account_Id)
    public ResponseEntity<Object> getAllTripByUserId(
            @PathVariable("account_id") int accountId
    ) {
        Driver driver = driverService.getDriverByAccountId(accountId);
        Rider rider = riderService.getRiderByUserId(accountId);

        if (driver == null && rider == null) {
            JSONObject object = new JSONObject();
            object.put("error", "Not Found");
            return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
        } else if (driver != null && rider == null) {
            return new ResponseEntity<>(service.getTripByDriverId(driver.id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.getTripByRiderId(rider.id), HttpStatus.OK);
        }
    }

    @RequestMapping(
            path = "/trip",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> createTrip(
            @RequestBody TripForm payload
    ) {
        Rider rider = null;
        try {
            rider = riderService.getRiderByUserId(payload.accountId);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        if (rider != null) {

            if (!service.getRiderTripsByStatusId(rider.id,2).isEmpty() ||
                    !service.getRiderTripsByStatusId(rider.id,4).isEmpty() ||
                    !service.getRiderTripsByStatusId(rider.id,3).isEmpty()
            ) {
                JSONObject object = new JSONObject();
                object.put("error", "You already have request in progress");
                return new ResponseEntity<>(object.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
            //check if rider does not have a trip
            List<Driver> drivers = driverService.getDriverByCityId(rider.cityId);

            // maybe we have a drivers but they are not available
            if (!drivers.isEmpty()) {

                Driver driver = null; //this is our driver that we will join him the trip
                for (Driver d : drivers) {
                    if (d.available) {
                        //Check if driver does not have a trip
                        if (!service.getTripsByDriverIdAndStatusId(d.id,4).isEmpty() ||
                                !service.getTripsByDriverIdAndStatusId(d.id,2).isEmpty() ||
                                !service.getTripsByDriverIdAndStatusId(d.id,3).isEmpty()
                        ) {
                            continue;
                        }
                        driver = d;
                        driver.available = false;
                        break;
                    }
                }//here add this condition
                if (driver == null) {
                    JSONObject object = new JSONObject();
                    object.put("error", "There are no available drivers right now.");
                    return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
                } else {
                    //create a trip
                    Trip trip = new Trip(
                            payload.pickLocLng,
                            payload.pickLocLat,
                            payload.dropLocLng,
                            payload.dropLocLat,
                            4,
                            driver.id,
                            rider.id
                    );

                    return new ResponseEntity<>(service.save(trip), HttpStatus.CREATED);
                }
            } else {
                JSONObject object = new JSONObject();
                object.put("error", "There are no available drivers in your city.");
                return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
            }
        } else {
            JSONObject object = new JSONObject();
            object.put("error", "Something wrong.");
            return new ResponseEntity<>(object.toString(), HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(
            path = "/start_trip/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Object> startTrip(@PathVariable("id") int id) {
        Trip trip = service.getTripById(id);
        Calendar calendar = Calendar.getInstance();
        if (trip != null && trip.statusId==4){
            trip.actPickLocLat = trip.pickLocLat;
            trip.actPickLocLng = trip.pickLocLng;
            trip.pickTime = calendar.getTime();
            trip.statusId = 2;
            return new ResponseEntity<>(service.save(trip),HttpStatus.OK);

        }else{
            JSONObject object = new JSONObject();
            object.put("error", "Something went Wrong.");
            return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            path = "/cancel_trip/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Trip> cancelTrip(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.updateStatus(id, 1),HttpStatus.OK);
    }

    @RequestMapping(
            path = "/finish_trip/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Object> finishTrip(
            @PathVariable("id") int id
    ) {
        Trip trip = service.getTripById(id);
        Calendar calendar = Calendar.getInstance();
        if (trip != null && trip.statusId==2) {
            trip.statusId = 3;
            trip.actDropLocLng = trip.dropLocLng;
            trip.actDropLocLat = trip.dropLocLat;
            trip.dropTime = calendar.getTime();
            //distance
            trip.distance = (float) distance(trip.actPickLocLat, trip.actPickLocLng, trip.actDropLocLat, trip.actDropLocLng);

            trip.duration = trip.distance * 2 ;
            trip.fare =  (trip.distance * 4.87f) + 10.0f;
            trip.statusId = 3;
            return new ResponseEntity<>(service.save(trip),HttpStatus.OK);
        }else{
            JSONObject object = new JSONObject();
            object.put("error", "Something Wrong.");
            return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
        }
    }



    // Method calculate distance in km
    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515 * 1.609344;
            return (dist);
        }
    }
}