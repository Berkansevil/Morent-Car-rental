package com.archisacademy.morent.exceptions;

import com.archisacademy.morent.exceptions.book.BookingNotFoundException;
import com.archisacademy.morent.exceptions.book.VehicleAlreadyBookedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Error message template
    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String error, Object message) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", status.value());
        errorDetails.put("error", error);
        errorDetails.put("message", message);
        return ResponseEntity.status(status).body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Validation Error", errors.toString());
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<?> handleVehicleNotFound(VehicleNotFoundException ex) {
        log.info("Vehicle not found: {}", ex.getMessage());
        return buildResponseEntity(HttpStatus.NOT_FOUND, "Vehicle Not Found", ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        log.warn("User registration conflict: {}", ex.getMessage());
        return buildResponseEntity(HttpStatus.CONFLICT, "User Conflict", ex.getMessage());
    }

    @ExceptionHandler(VehicleAlreadyBookedException.class)
    public ResponseEntity<String> handleVehicleAlreadyBookedException(VehicleAlreadyBookedException ex) {
        log.warn("Vehicle already booked: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException ex) {
        log.info("Booking not found: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<String> handlePaymentNotFoundException(PaymentNotFoundException ex) {
        log.info("Payment not found: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
