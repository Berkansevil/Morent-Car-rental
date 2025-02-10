package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.BookingRequest;
import com.archisacademy.morent.dtos.requests.BookingStatusRequest;
import com.archisacademy.morent.dtos.responses.BookingDetailsResponse;
import com.archisacademy.morent.dtos.responses.BookingResponse;
import com.archisacademy.morent.dtos.responses.BookingStatusResponse;


public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    String deleteBooking(Long id);
    BookingDetailsResponse getBookingDetails(Long id);

    BookingStatusResponse checkAvailability(BookingStatusRequest request);
}

