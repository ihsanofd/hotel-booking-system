package com.hotel.Hotel.Controller;

import com.hotel.Hotel.Dto.LoginRequest;
import com.hotel.Hotel.Dto.UserRequest;
import com.hotel.Hotel.Dto.UserResponse;
import com.hotel.Hotel.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/user")
    private ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        UserResponse userResponse= userService.addUser(request);
        return new ResponseEntity<>(userResponse , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return "login successful";
        }
        return "login fail";
    }
}
