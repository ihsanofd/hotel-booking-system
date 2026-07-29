package com.hotel.Hotel.Service;

import com.hotel.Hotel.Dto.HotelRequest;
import com.hotel.Hotel.Dto.HotelResponse;
import com.hotel.Hotel.Model.Hotel;
import com.hotel.Hotel.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    public HotelResponse addHotel(HotelRequest request) {

        Hotel hotel=new Hotel();

        hotel.setHotelName(request.getHotelName());
        hotel.setCity(request.getCity());
        hotel.setAddress(request.getAddress());
        hotel.setRating(request.getRating());
        hotel.setDescription(request.getDescription());

        Hotel saved=hotelRepository.save(hotel);


        HotelResponse hotelResponse=new HotelResponse();

        hotelResponse.setId(saved.getId());
        hotelResponse.setHotelName(saved.getHotelName());
        hotelResponse.setCity(saved.getCity());
        hotelResponse.setAddress(saved.getAddress());
        hotelResponse.setDescription(saved.getDescription());
        hotelResponse.setRating(saved.getRating());

        return hotelResponse;
    }



    public List<HotelResponse> findHotelsInCity(String cityName) {

        List<Hotel> hotels=hotelRepository.findByCityIgnoreCase(cityName);
        List<HotelResponse> hotelResponses=new ArrayList<>();

            for (Hotel hotel : hotels) {

                HotelResponse response = new HotelResponse();

                response.setId(hotel.getId());
                response.setCity(hotel.getCity());
                response.setHotelName(hotel.getHotelName());
                response.setAddress(hotel.getAddress());
                response.setDescription(hotel.getDescription());
                response.setRating(hotel.getRating());
                hotelResponses.add(response);
        }

        return hotelResponses;
    }




    public List<HotelResponse> findHotel(String hotelName) {
        List<Hotel> hotels= hotelRepository.findByHotelNameContainingIgnoreCase(hotelName);

       List<HotelResponse> hotelResponse=new ArrayList<>();

        for(Hotel hotel : hotels){
            HotelResponse response=new HotelResponse();

            response.setId(hotel.getId());
            response.setHotelName(hotel.getHotelName());
            response.setCity(hotel.getCity());
            response.setAddress(hotel.getAddress());
            response.setDescription(hotel.getDescription());
            response.setRating(hotel.getRating());

            hotelResponse.add(response);
        }

        return hotelResponse;
    }



    public List<HotelResponse> findAllHotels() {

        List<Hotel> hotels=hotelRepository.findAll();

        List<HotelResponse> hotelResponses=new ArrayList<>();

        for (Hotel hotel:hotels){
            HotelResponse response=new HotelResponse();

            response.setId(hotel.getId());
            response.setHotelName(hotel.getHotelName());
            response.setCity(hotel.getCity());
            response.setAddress(hotel.getAddress());
            response.setDescription(hotel.getDescription());
            response.setRating(hotel.getRating());

            hotelResponses.add(response);
        }
        return hotelResponses;
    }
}
