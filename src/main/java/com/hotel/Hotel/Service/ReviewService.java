package com.hotel.Hotel.Service;

import com.hotel.Hotel.Dto.ReviewRequest;
import com.hotel.Hotel.Dto.ReviewResponse;
import com.hotel.Hotel.Exception.BookingNotFoundException;
import com.hotel.Hotel.Exception.HotelNotFoundException;
import com.hotel.Hotel.Model.Booking;
import com.hotel.Hotel.Model.Hotel;
import com.hotel.Hotel.Model.Review;
import com.hotel.Hotel.Repository.BookingRepository;
import com.hotel.Hotel.Repository.HotelRepository;
import com.hotel.Hotel.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public ReviewResponse addReview(ReviewRequest request) {
        Booking booking=bookingRepository.findById(request.getBookingId()).orElseThrow(
                ()->new BookingNotFoundException("booking is not found for id : " + request.getBookingId()));

        Review review=new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(LocalDate.now());
        review.setBooking(booking);

        Review saved=reviewRepository.save(review);

        ReviewResponse response=new ReviewResponse();
        response.setId(saved.getId());
        response.setComment(saved.getComment());
        response.setRating(saved.getRating());
        response.setHotelName(saved.getBooking().getRoom().getHotel().getHotelName());

        return response;
    }



    public List<ReviewResponse> Reviews(Integer hotelId) {

        Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(
                ()->new HotelNotFoundException("hotel Not found"));

        List<Review> reviews=reviewRepository.findByBooking_Room_Hotel_Id(hotelId);

        List<ReviewResponse> responses=new ArrayList<>();

        for (Review review:reviews){
            ReviewResponse response=new ReviewResponse();

            response.setId(review.getId());
            response.setRating(review.getRating());
            response.setComment(review.getComment());
            response.setHotelName(review.getBooking().getRoom().getHotel().getHotelName());

            responses.add(response);
        }
        return responses;
    }
}
