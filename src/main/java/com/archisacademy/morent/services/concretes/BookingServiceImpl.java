package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.ModelMappper.ModelMapperServiceImpl;
import com.archisacademy.morent.dtos.requests.BookingRequest;
import com.archisacademy.morent.dtos.requests.NotificationRequest;
import com.archisacademy.morent.dtos.requests.BookingStatusRequest;
import com.archisacademy.morent.dtos.responses.BookingDetailsResponse;
import com.archisacademy.morent.dtos.responses.BookingResponse;
import com.archisacademy.morent.dtos.responses.BookingStatusResponse;
import com.archisacademy.morent.entities.Booking;
import com.archisacademy.morent.entities.User;
import com.archisacademy.morent.entities.Vehicle;
import com.archisacademy.morent.exceptions.book.BookingNotFoundException;
import com.archisacademy.morent.exceptions.book.VehicleAlreadyBookedException;
import com.archisacademy.morent.repositories.BookingRepository;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.repositories.VehicleRepository;
import com.archisacademy.morent.services.abstracts.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapperServiceImpl modelMapperService;
    private final NotificationServiceImpl notificationService;

    @Override
    public BookingResponse createBooking(BookingRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepository.findByVehicleId(request.getVehicleId()).orElseThrow(
                () -> new RuntimeException("Vehicle not found"));

            LocalDate  startDate = LocalDate.parse(request.getStartDate());
           LocalDate endDate = LocalDate.parse(request.getEndDate());

        List<Booking> conflictBookings = bookingRepository.findConflictingBookings
                (request.getVehicleId(), startDate, endDate);
        if (!conflictBookings.isEmpty()) {
            throw new VehicleAlreadyBookedException("This vehicle already exists in the booking");
        }

        Booking booking = modelMapperService.request().map(request, Booking.class);
        booking.setUser(user);
        booking.setVehicle(vehicle);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        BigDecimal totalAmount = BigDecimal.valueOf(vehicle.getDailyRate() * days);
        booking.setTotalAmount(totalAmount);
        bookingRepository.save(booking);
        String message = "Your booking for vehicle " + vehicle.getVehicleId() + " has been confirmed";
        NotificationRequest notificationRequest = new NotificationRequest(user.getUserId(), message);
        notificationService.createNotification(notificationRequest);
        return new BookingResponse("Booking successful", booking.getBookingId(), booking.getTotalAmount());
    }

    @Override
    public String deleteBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            bookingRepository.deleteById(id);
            return "Booking successfully deleted";
        } else {
            return "Booking not found with the given ID";
        }
    }

    @Override
    public BookingDetailsResponse getBookingDetails(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);

        Booking booking = bookingOptional.orElseThrow(() -> new
                BookingNotFoundException("Booking not found with the given ID"));
        BookingDetailsResponse bookingResponse=modelMapperService.request().map(booking, BookingDetailsResponse.class);
        bookingResponse.setStartDate(booking.getStartDate());
        bookingResponse.setEndDate(booking.getEndDate());
        return bookingResponse;
    }

    @Override
    public BookingStatusResponse checkAvailability(BookingStatusRequest request) {
        LocalDate startDate = LocalDate.parse(request.getStartDate());
        LocalDate endDate = LocalDate.parse(request.getEndDate());
        List<Booking> conflictingBookings = bookingRepository.findConflictingBookings(request.getVehicleId(), startDate, endDate);
        if (conflictingBookings.isEmpty()) {
            return new BookingStatusResponse(true);
        } else {
            return new BookingStatusResponse(false);
        }
    }
}
