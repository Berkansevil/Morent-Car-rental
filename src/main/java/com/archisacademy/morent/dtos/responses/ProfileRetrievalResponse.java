package com.archisacademy.morent.dtos.responses;

import com.archisacademy.morent.entities.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRetrievalResponse {

    private UUID userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private List<BookingDetailsResponse> bookings;
}
