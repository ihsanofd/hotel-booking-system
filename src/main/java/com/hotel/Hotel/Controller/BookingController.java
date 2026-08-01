package com.hotel.Hotel.Controller;

import com.hotel.Hotel.Dto.BookingRequest;
import com.hotel.Hotel.Dto.BookingResponse;
import com.hotel.Hotel.Enum.BookingStatus;
import com.hotel.Hotel.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> booking(@RequestBody BookingRequest request){
        BookingResponse response=bookingService.addBooking(request);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings(){
        List<BookingResponse> responses=bookingService.allBookings();
        return new ResponseEntity<>(responses , HttpStatus.OK);
    }

    @GetMapping("userId/{userId}")
    public ResponseEntity<List<BookingResponse>> getAllBookings(@PathVariable Integer userId){
        List<BookingResponse> responses=bookingService.allBookingsRelatedToUser(userId);
        return new ResponseEntity<>(responses , HttpStatus.OK);
    }

    @GetMapping("bookingId/{bookingId}")
    public ResponseEntity<BookingResponse> BookingById(@PathVariable Integer bookingId){
       BookingResponse responses=bookingService.bookingById(bookingId);
        return new ResponseEntity<>(responses , HttpStatus.OK);
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingResponse> updateStatus(@PathVariable Integer bookingId , @RequestParam BookingStatus status){
        BookingResponse response=bookingService.updateStatus(bookingId ,status);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

}
