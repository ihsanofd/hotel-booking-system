package com.hotel.Hotel.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequest {

    private Integer roomId;
    private Integer userId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
