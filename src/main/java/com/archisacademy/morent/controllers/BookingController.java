package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.requests.BookingRequest;
import com.archisacademy.morent.dtos.requests.BookingStatusRequest;
import com.archisacademy.morent.dtos.responses.BookingDetailsResponse;
import com.archisacademy.morent.dtos.responses.BookingResponse;
import com.archisacademy.morent.dtos.responses.BookingStatusResponse;
import com.archisacademy.morent.services.concretes.BookingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingServiceImpl bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.status(HttpStatus.OK).body("Booking cancelled successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDetailsResponse> getDetailBooking(@PathVariable Long id) {
        BookingDetailsResponse bookingResponse = bookingService.getBookingDetails(id);
        return ResponseEntity.ok(bookingResponse);
    }
    @PostMapping("/check-availability")
    public ResponseEntity<BookingStatusResponse> checkAvailability(@RequestBody BookingStatusRequest request) {
        BookingStatusResponse response = bookingService.checkAvailability(request);
        return ResponseEntity.ok(response);
    }
}
