package com.hotel.Hotel.Controller;

import com.hotel.Hotel.Dto.RoomRequest;
import com.hotel.Hotel.Dto.RoomResponse;
import com.hotel.Hotel.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("isAvailable")
    public ResponseEntity<List<RoomResponse>> isAvailable(
            @RequestParam LocalDate checkInDate ,
            @RequestParam LocalDate checkOutDate){

        List<RoomResponse> response=roomService.isAvailable(checkInDate , checkOutDate);
        return new ResponseEntity<>(response , HttpStatus.OK);

    }
}
