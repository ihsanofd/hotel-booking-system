package com.hotel.Hotel.Service;

import com.hotel.Hotel.Dto.PaymentRequest;
import com.hotel.Hotel.Dto.PaymentResponse;
import com.hotel.Hotel.Enum.BookingStatus;
import com.hotel.Hotel.Exception.BookingNotFoundException;
import com.hotel.Hotel.Exception.PaymentAlreadyExistException;
import com.hotel.Hotel.Model.Booking;
import com.hotel.Hotel.Model.Payment;
import com.hotel.Hotel.Repository.BookingRepository;
import com.hotel.Hotel.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public PaymentResponse createPayment(PaymentRequest request) {

        Booking booking=bookingRepository.findById(request.getBookingId()).orElseThrow(
                ()->new BookingNotFoundException("Booking Not Found")
        );

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new IllegalArgumentException("Payment can only be made for confirmed bookings");
        }

        Optional<Payment> existing=paymentRepository.findByBookingId(booking.getId());
        if (existing.isPresent()){
            throw new PaymentAlreadyExistException("This booking already has a payment");
        }

        Payment payment = new Payment();
            payment.setBooking(booking);
            Payment saved=paymentRepository.save(payment);

            PaymentResponse response=new PaymentResponse();
            response.setId(saved.getId());
            response.setUserId(saved.getBooking().getUser().getId());
            response.setBookingId(saved.getBooking().getId());
            response.setTotalPrice(saved.getBooking().getTotalPrice());
            response.setHotelName(saved.getBooking().getRoom().getHotel().getHotelName());
            response.setUsername(saved.getBooking().getUser().getUsername());

            return response;
    }
}
