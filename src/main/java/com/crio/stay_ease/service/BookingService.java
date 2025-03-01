package com.crio.stay_ease.service;

import com.crio.stay_ease.dto.BookingResponseDto;
import com.crio.stay_ease.entity.Booking;
import com.crio.stay_ease.entity.Hotel;
import com.crio.stay_ease.entity.User;
import com.crio.stay_ease.exceptions.NotFoundException;
import com.crio.stay_ease.respository.BookingRepository;
import com.crio.stay_ease.respository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;

    public BookingService(BookingRepository bookingRepository, HotelRepository hotelRepository) {
        this.bookingRepository = bookingRepository;
        this.hotelRepository = hotelRepository;
    }


    public BookingResponseDto makeBooking(User user, Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new NotFoundException("hotel with id: " + hotelId + " not found.")
        );

        if(hotel.getAvailableRooms() > 0) {
            hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
            hotelRepository.save(hotel);

            Booking booking = Booking.builder()
                    .user(user)
                    .hotel(hotel)
                    .date(new Date())
                    .build();

            booking = bookingRepository.save(booking);

            return BookingResponseDto.builder()
                    .userId(booking.getUser().getId())
                    .userName(booking.getUser().getFirstName() + " " + booking.getUser().getLastName())
                    .userEmail(booking.getUser().getEmail())
                    .hotel(hotel)
                    .date(booking.getDate())
                    .build();


        }else {
            throw new NotFoundException("No rooms available right now");
        }
    }

    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new NotFoundException("Booking with bookingId: " + bookingId + " not found")
        );

        Hotel hotel = booking.getHotel();
        hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
        hotelRepository.save(hotel);

        bookingRepository.deleteById(bookingId);

    }
}
