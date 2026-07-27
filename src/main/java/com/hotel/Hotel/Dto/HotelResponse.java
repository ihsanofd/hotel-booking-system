package com.hotel.Hotel.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HotelResponse {

    private Integer id;
    private String hotelName;
    private String city;
    private String address;
    private Double rating;
    private String description;

}
