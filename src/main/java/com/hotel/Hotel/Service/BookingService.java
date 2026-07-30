package com.hotel.Hotel.Service;

import com.hotel.Hotel.Dto.BookingRequest;
import com.hotel.Hotel.Dto.BookingResponse;
import com.hotel.Hotel.Enum.BookingStatus;
import com.hotel.Hotel.Exception.BookingNotFoundException;
import com.hotel.Hotel.Exception.RoomNotAvailableException;
import com.hotel.Hotel.Exception.RoomNotFoundException;
import com.hotel.Hotel.Exception.UserNotFoundException;
import com.hotel.Hotel.Model.Booking;
import com.hotel.Hotel.Model.Room;
import com.hotel.Hotel.Model.User;
import com.hotel.Hotel.Repository.BookingRepository;
import com.hotel.Hotel.Repository.RoomRepository;
import com.hotel.Hotel.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;


    public BookingResponse addBooking(BookingRequest request) {

        User user=userRepository.findById(request.getUserId()).orElseThrow(
                ()->new UserNotFoundException("user not found at this id"));


            Room room = roomRepository.findById(request.getRoomId()).orElseThrow(
                    () -> new RoomNotFoundException("Room not found at this id :" + request.getRoomId()));


        Integer roomId=request.getRoomId();
        LocalDate checkIn=request.getCheckInDate();
        LocalDate checkOut=request.getCheckOutDate();

        List<Booking> conflicts = bookingRepository.findOverlappingBookings(roomId, checkIn, checkOut);
        if (!conflicts.isEmpty()) {
            throw new RoomNotAvailableException("Room is already booked for these dates");
        }


        Booking booking=new Booking();

        booking.setRoom(room);
        booking.setUser(user);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());

        long nights= ChronoUnit.DAYS.between(
                request.getCheckInDate(),
                request.getCheckOutDate());

        booking.setTotalPrice(room.getPricePerNight() * nights);

        Booking saved=bookingRepository.save(booking);



        BookingResponse response=new BookingResponse();

        response.setId(saved.getId());
        response.setHotelName(room.getHotel().getHotelName());
        response.setUserId(saved.getUser().getId());
        response.setRoomId(saved.getRoom().getId());
        response.setCheckInDate(saved.getCheckInDate());
        response.setCheckOutDate(saved.getCheckOutDate());
        response.setStatus(saved.getStatus());
        response.setRoomType(saved.getRoom().getRoomType());
        response.setTotalPrice(saved.getTotalPrice());

        return response;

    }


    public List<BookingResponse> allBookings() {

        List<Booking> bookings=bookingRepository.findAll();

        List<BookingResponse> bookingResponses=new ArrayList<>();

        for (Booking booking:bookings){

            BookingResponse response=new BookingResponse();
            response.setId(booking.getId());
            response.setUserId(booking.getUser().getId());
            response.setRoomId(booking.getRoom().getId());
            response.setRoomType(booking.getRoom().getRoomType());
            response.setCheckInDate(booking.getCheckInDate());
            response.setCheckInDate(booking.getCheckInDate());
            response.setCheckOutDate(booking.getCheckOutDate());
            response.setStatus(booking.getStatus());
            response.setHotelName(booking.getRoom().getHotel().getHotelName());
            response.setTotalPrice(booking.getTotalPrice());

            bookingResponses.add(response);
        }

        return bookingResponses;
    }

    public List<BookingResponse> allBookingsRelatedToUser(Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not exist for id :" + userId));

        List<Booking> bookings = bookingRepository.findBookingByUserId(userId);

        List<BookingResponse> bookingResponses=new ArrayList<>();

        for (Booking booking: bookings){

            BookingResponse response=new BookingResponse();
            response.setId(booking.getId());
            response.setUserId(booking.getUser().getId());
            response.setRoomId(booking.getRoom().getId());
            response.setRoomType(booking.getRoom().getRoomType());
            response.setCheckInDate(booking.getCheckInDate());
            response.setCheckInDate(booking.getCheckInDate());
            response.setCheckOutDate(booking.getCheckOutDate());
            response.setStatus(booking.getStatus());
            response.setHotelName(booking.getRoom().getHotel().getHotelName());
            response.setTotalPrice(booking.getTotalPrice());

            bookingResponses.add(response);
        }

        return bookingResponses;
    }

    public BookingResponse bookingById(Integer bookingId) {

        Booking booking=bookingRepository.findById(bookingId).orElseThrow(
                ()->new BookingNotFoundException("booking not found for this id :"+ bookingId));

        BookingResponse response=new BookingResponse();
        response.setId(booking.getId());
        response.setUserId(booking.getUser().getId());
        response.setRoomId(booking.getRoom().getId());
        response.setRoomType(booking.getRoom().getRoomType());
        response.setCheckInDate(booking.getCheckInDate());
        response.setCheckInDate(booking.getCheckInDate());
        response.setCheckOutDate(booking.getCheckOutDate());
        response.setStatus(booking.getStatus());
        response.setHotelName(booking.getRoom().getHotel().getHotelName());
        response.setTotalPrice(booking.getTotalPrice());

        return  response;
    }


    public BookingResponse updateStatus(Integer bookingId ,BookingStatus status) {

        Booking booking=bookingRepository.findById(bookingId).orElseThrow(
                ()->new BookingNotFoundException("Booking not found for id :" + bookingId));

        booking.setStatus(status);
        Booking saved=bookingRepository.save(booking);

        BookingResponse response=new BookingResponse();
        response.setId(booking.getId());
        response.setUserId(booking.getUser().getId());
        response.setRoomId(booking.getRoom().getId());
        response.setRoomType(booking.getRoom().getRoomType());
        response.setCheckInDate(booking.getCheckInDate());
        response.setCheckInDate(booking.getCheckInDate());
        response.setCheckOutDate(booking.getCheckOutDate());
        response.setStatus(booking.getStatus());
        response.setHotelName(booking.getRoom().getHotel().getHotelName());
        response.setTotalPrice(booking.getTotalPrice());

        return  response;

    }
}
