package com.hotel.Hotel.Controller;

import com.hotel.Hotel.Dto.PaymentRequest;
import com.hotel.Hotel.Dto.PaymentResponse;
import com.hotel.Hotel.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request){
        PaymentResponse response=paymentService.createPayment(request);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }
}
