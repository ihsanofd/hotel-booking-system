package com.hotel.Hotel.Service;

import com.hotel.Hotel.Dto.UserRequest;
import com.hotel.Hotel.Dto.UserResponse;
import com.hotel.Hotel.Enum.Role;
import com.hotel.Hotel.Model.User;
import com.hotel.Hotel.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserResponse addUser(UserRequest request) {

        User user=new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(Role.GUEST);

        User saved=userRepository.save(user);


        UserResponse userResponse=new UserResponse();
        userResponse.setId(saved.getId());
        userResponse.setUsername(saved.getUsername());

        return userResponse;
    }
}
