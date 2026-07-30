package com.hotel.Hotel.Controller;

import com.hotel.Hotel.Dto.ReviewRequest;
import com.hotel.Hotel.Dto.ReviewResponse;
import com.hotel.Hotel.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(@RequestBody ReviewRequest request){
        ReviewResponse response=reviewService.addReview(request);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<List<ReviewResponse>> hotelReviews(@PathVariable Integer hotelId){
        List<ReviewResponse> responses=reviewService.Reviews(hotelId);
        return new ResponseEntity<>(responses , HttpStatus.OK);
    }
}
