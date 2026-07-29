package com.hotel.Hotel.Controller;

import com.hotel.Hotel.Dto.HotelRequest;
import com.hotel.Hotel.Dto.HotelResponse;
import com.hotel.Hotel.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponse> addHotel(@RequestBody HotelRequest request){
        HotelResponse hotelResponse=hotelService.addHotel(request);
        return new ResponseEntity<>(hotelResponse , HttpStatus.CREATED);
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<List<HotelResponse>> getInCity(@PathVariable String cityName){
        List<HotelResponse> responses=hotelService.findHotelsInCity(cityName);
        return new ResponseEntity<>(responses , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<HotelResponse>> findHotelByName(@RequestParam String hotelName){
        List<HotelResponse> responses = hotelService.findHotel(hotelName);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> findHotels(){
        List<HotelResponse> responses=hotelService.findAllHotels();
        return new ResponseEntity<>(responses , HttpStatus.CREATED);
    }
}
