package com.hotel.Hotel.Service;

import com.hotel.Hotel.Dto.RoomRequest;
import com.hotel.Hotel.Dto.RoomResponse;
import com.hotel.Hotel.Model.Hotel;
import com.hotel.Hotel.Model.Room;
import com.hotel.Hotel.Repository.BookingRepository;
import com.hotel.Hotel.Repository.HotelRepository;
import com.hotel.Hotel.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private BookingRepository bookingRepository;


    public RoomResponse addRoom(RoomRequest request) {

        Hotel hotel=hotelRepository.findHotelById(request.getHotelId()).orElseThrow(
                ()->new IllegalArgumentException("hotel not found"));


        Room room=new Room();

        room.setRoomType(request.getRoomType());
        room.setCapacity(request.getCapacity());
        room.setPricePerNight(request.getPricePerNight());
        room.setHotel(hotel);
        room.setRoomNumber(request.getRoomNumber());
        Room saved=roomRepository.save(room);


        RoomResponse response=new RoomResponse();

        response.setId(saved.getId());
        response.setCapacity(saved.getCapacity());
        response.setPricePerNight(saved.getPricePerNight());
        response.setHotelName(saved.getHotel().getHotelName());
        response.setRoomType(saved.getRoomType());
        response.setRoomNumber(saved.getRoomNumber());

        return response;
    }



    public List<RoomResponse> isAvailable(
            LocalDate checkInDate,
            LocalDate checkOutDate) {

        List<Room> rooms=roomRepository.findAll();
        List<RoomResponse> availableRooms=new ArrayList<>();


        for (Room room:rooms) {
            List<Room> available=roomRepository.findAvailableRooms(room.getId() ,checkInDate , checkOutDate);
            if (available.isEmpty()) {

                RoomResponse response = new RoomResponse();

                response.setId(room.getId());
                response.setRoomNumber(room.getRoomNumber());
                response.setCapacity(room.getCapacity());
                response.setRoomType(room.getRoomType());
                response.setPricePerNight(room.getPricePerNight());
                response.setHotelName(room.getHotel().getHotelName());

                availableRooms.add(response);
            }
        }

        return availableRooms;
    }
}
