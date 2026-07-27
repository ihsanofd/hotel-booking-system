package com.hotel.Hotel.Repository;

import com.hotel.Hotel.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
