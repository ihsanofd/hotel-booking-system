package com.hotel.Hotel.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponse {

    private Integer id;
    private Integer userId;
    private String username;
    private Integer bookingId;
    private Double totalPrice;
    private String hotelName;

}
