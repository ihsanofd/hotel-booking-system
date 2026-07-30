package com.hotel.Hotel.Repository;

import com.hotel.Hotel.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId " +
            "AND b.status NOT IN ('CANCELLED' , 'COMPLETED')" +
            "AND b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate")

    List<Booking> findOverlappingBookings(
            @Param("roomId") Integer roomId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );

    List<Booking> findBookingByUserId(Integer userId);
}
