package com.uber.uber.controllers;

import com.uber.uber.form.PaymentForm;
import com.uber.uber.form.TripForm;
import com.uber.uber.models.*;
import com.uber.uber.service.*;
import org.checkerframework.checker.units.qual.A;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(
        maxAge = 3600
)
@RestController
public class PaymentController {
    @Autowired
    private PaymentService service;
    @Autowired
    private TripService tripService;
    @Autowired
    private RiderWalletService riderWalletServiceService;

    @Autowired
    private DriverWalletService driverWalletService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/payments")
    public List<Payment> getPayments() {
        return service.getPayments();
    }





    @RequestMapping(
            path = "/payment",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> makePayment(
            @RequestBody PaymentForm payload
    ) {
        if (service.getPaymentByTrip(payload.getTripId()) != null) {
            // return error
            JSONObject object = new JSONObject();
            object.put("error", "This trip has been paid");
            return new ResponseEntity<>(object.toString(), HttpStatus.FORBIDDEN);
        } else {
            Trip trip = null;
            try {
                trip = tripService.getTripById(payload.getTripId());
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
            if (trip != null) {
                //payment method wallet
              if (payload.getPaymentMethodId() == 2){
                  RiderWallet riderWallet = riderWalletServiceService.getWalletByRiderId(trip.riderId);
                  if(riderWallet.balance >= trip.fare){
                      riderWallet.balance = riderWallet.balance - trip.fare;
                      riderWalletServiceService.save(riderWallet);
                      Payment payment = createPayment(new Payment(trip.fare,2,trip.riderId,trip.id));
                      DriverWallet driverWallet = driverWalletService.getWalletByDriverId(trip.driverId);
                      driverWallet.balance += trip.fare;
                      driverWalletService.save(driverWallet);
                      makeTransaction(trip.driverId,trip.id,trip.fare * 0.1f);
                      return new ResponseEntity<>(payment,HttpStatus.CREATED);
                  }else{
                      JSONObject object = new JSONObject();
                      object.put("error", "Your wallet balance isn't enough");
                      return new ResponseEntity<>(object.toString(), HttpStatus.FORBIDDEN);
                  }

              }else{
                  if(payload.getValue() == trip.fare){
                      Payment payment = createPayment(new Payment(trip.fare,1,trip.riderId,trip.id));
                      makeTransaction(trip.driverId,trip.id,trip.fare * 0.1f);
                      return new ResponseEntity<>(payment,HttpStatus.CREATED);
                  }else if(payload.getValue() > trip.fare){
                      RiderWallet riderWallet = riderWalletServiceService.getWalletByRiderId(trip.riderId);
                      riderWallet.balance += (payload.getValue() - trip.fare);
                      riderWalletServiceService.save(riderWallet);
                      Payment payment = createPayment(new Payment(trip.fare,1,trip.riderId,trip.id));
                      makeTransaction(trip.driverId,trip.id,trip.fare * 0.1f);
                      return new ResponseEntity<>(payment,HttpStatus.CREATED);
                  }else{
                      JSONObject object = new JSONObject();
                      object.put("error", "This value is less than fare");
                      return new ResponseEntity<>(object.toString(), HttpStatus.FORBIDDEN);
                  }
              }
            }else {
                JSONObject object = new JSONObject();
                object.put("error", "This trip does not exist");
                return new ResponseEntity<>(object.toString(), HttpStatus.NOT_FOUND);
            }
        }
    }

    private Payment createPayment(Payment payment){
        tripService.updateStatus(payment.tripId,5);
        return service.save(payment);
    }
    private Transaction makeTransaction(
            int driverId,
            int tripId,
            float value
    ){
        Transaction transaction = transactionService.save(new Transaction(tripId,value,driverId));
        DriverWallet driverWallet = driverWalletService.getWalletByDriverId(driverId);
        driverWallet.balance -= value;
        driverWalletService.save(driverWallet);
        return transaction;
    }
}
