package com.hotel.Hotel.Controller;

import com.hotel.Hotel.Dto.RoomRequest;
import com.hotel.Hotel.Dto.RoomResponse;
import com.hotel.Hotel.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> addRoom(@RequestBody RoomRequest request){
        RoomResponse response=roomService.addRoom(request);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }
}
