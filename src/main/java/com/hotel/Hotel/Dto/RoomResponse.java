package com.hotel.Hotel.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoomResponse {

    private Integer id;
    private Integer capacity;
    private String roomType;
    private Double pricePerNight;
    private String hotelName;

}
