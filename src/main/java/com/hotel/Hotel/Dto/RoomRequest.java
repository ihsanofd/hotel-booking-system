package com.hotel.Hotel.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

    private Integer capacity;
    private String roomType;
    private Double pricePerNight;
    private Integer hotelId;

}
