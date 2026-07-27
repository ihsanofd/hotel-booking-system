package com.hotel.Hotel.Repository;

import com.hotel.Hotel.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

      Optional<Hotel> findHotelById(Integer hotelId);

      List<Hotel> findByCityIgnoreCase(String cityName);
}
