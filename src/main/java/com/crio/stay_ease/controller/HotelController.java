package com.crio.stay_ease.controller;

import com.crio.stay_ease.entity.Hotel;
import com.crio.stay_ease.respository.HotelRepository;
import com.crio.stay_ease.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.findAll();
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.save(hotel), HttpStatus.CREATED);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.update(hotelId, hotel), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable String hotelId) {
        hotelService.delete(hotelId);
        return ResponseEntity.noContent().build();
    }

}
