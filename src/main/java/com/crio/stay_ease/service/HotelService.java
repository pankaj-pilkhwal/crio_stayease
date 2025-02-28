package com.crio.stay_ease.service;

import com.crio.stay_ease.entity.Hotel;
import com.crio.stay_ease.exceptions.NotFoundException;
import com.crio.stay_ease.respository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel update(String hotelId, Hotel hotel) {
        hotelRepository.findById(hotelId).orElseThrow(
                ()-> new NotFoundException("hotel with hotelId: " + hotelId + " not found.")
        );
        hotel.setId(hotelId);

        return hotelRepository.save(hotel);
    }

    public void delete(String hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}
