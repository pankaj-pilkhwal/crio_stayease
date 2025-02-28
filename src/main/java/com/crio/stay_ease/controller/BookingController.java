package com.crio.stay_ease.controller;

import com.crio.stay_ease.dto.BookingResponseDto;
import com.crio.stay_ease.entity.Booking;
import com.crio.stay_ease.entity.User;
import com.crio.stay_ease.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class BookingController {
    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/{hotelId}/book")
    public ResponseEntity<BookingResponseDto> makeBooking(User user, @PathVariable String hotelId) {
        BookingResponseDto booking = bookingService.makeBooking(user, hotelId);

        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }



}
