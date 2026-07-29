package com.hotel.Hotel.Dto;

import com.hotel.Hotel.Enum.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private Integer id;
    private Integer userId;
    private String hotelName;
    private Integer roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Double totalPrice;
    private BookingStatus status;
    private String roomType;
}
