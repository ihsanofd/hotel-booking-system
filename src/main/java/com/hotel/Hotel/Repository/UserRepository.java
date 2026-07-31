package com.hotel.Hotel.Repository;

import com.hotel.Hotel.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Integer> {

}
