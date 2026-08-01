package com.hotel.Hotel.Service;

import com.hotel.Hotel.Dto.PaymentRequest;
import com.hotel.Hotel.Dto.PaymentResponse;
import com.hotel.Hotel.Enum.BookingStatus;
import com.hotel.Hotel.Exception.BookingNotFoundException;
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
    @Autowired
    private BookingService bookingService;

    public PaymentResponse createPayment(PaymentRequest request) {

        Booking booking=bookingRepository.findById(request.getBookingId()).orElseThrow(
                ()->new BookingNotFoundException("Booking Not Found")
        );

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new IllegalArgumentException("Payment can only be made for pending bookings");
        }

        Optional<Payment> existing=paymentRepository.findByBookingId(booking.getId());

        Payment payment = new Payment();
            payment.setBooking(booking);
            Payment saved=paymentRepository.save(payment);
            bookingService.updateStatus(booking.getId(), BookingStatus.CONFIRMED);

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
