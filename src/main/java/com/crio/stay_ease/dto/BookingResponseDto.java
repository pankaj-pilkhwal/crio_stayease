package com.crio.stay_ease.dto;

import com.crio.stay_ease.entity.Hotel;
import com.crio.stay_ease.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BookingResponseDto {
    Long userId;

    String userName;

    String userEmail;

    private Hotel hotel;

    private Date date;
}
