package com.hotel.Hotel.Exception;

public class PaymentAlreadyExistException extends RuntimeException {
    public PaymentAlreadyExistException(String message) {
        super(message);
    }
}
