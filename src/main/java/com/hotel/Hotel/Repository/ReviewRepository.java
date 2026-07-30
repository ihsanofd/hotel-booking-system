package com.hotel.Hotel.Repository;

import com.hotel.Hotel.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByBooking_Room_Hotel_Id(Integer hotelId);
}
