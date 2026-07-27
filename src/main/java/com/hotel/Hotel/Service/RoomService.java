package com.hotel.Hotel.Service;

import com.hotel.Hotel.Dto.RoomRequest;
import com.hotel.Hotel.Dto.RoomResponse;
import com.hotel.Hotel.Model.Hotel;
import com.hotel.Hotel.Model.Room;
import com.hotel.Hotel.Repository.HotelRepository;
import com.hotel.Hotel.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public RoomResponse addRoom(RoomRequest request) {

        Hotel hotel=hotelRepository.findHotelById(request.getHotelId()).orElseThrow(
                ()->new IllegalArgumentException("hotel not found"));


        Room room=new Room();

        room.setRoomType(request.getRoomType());
        room.setCapacity(request.getCapacity());
        room.setPricePerNight(request.getPricePerNight());
        room.setHotel(hotel);
        Room saved=roomRepository.save(room);


        RoomResponse response=new RoomResponse();

        response.setId(saved.getId());
        response.setCapacity(saved.getCapacity());
        response.setPricePerNight(saved.getPricePerNight());
        response.setHotelName(saved.getHotel().getHotelName());
        response.setRoomType(saved.getRoomType());

        return response;
    }
}
