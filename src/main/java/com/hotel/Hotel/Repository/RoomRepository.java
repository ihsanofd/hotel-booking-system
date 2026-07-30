package com.hotel.Hotel.Repository;

import com.hotel.Hotel.Model.Booking;
import com.hotel.Hotel.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId " +
            "AND b.status NOT IN ('CANCELLED' , 'COMPLETED')" +
            "AND b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate")
    List<Room> findAvailableRooms(
            @Param("roomId") Integer roomId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );
}
