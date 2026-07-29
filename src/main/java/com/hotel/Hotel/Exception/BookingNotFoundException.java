package com.hotel.Hotel.Exception;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(String s) {
        super(s);
    }
}
