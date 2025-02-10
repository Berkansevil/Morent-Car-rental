package com.archisacademy.morent.dtos.requests;

import com.archisacademy.morent.entities.Booking;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


@Data
public class PaymentRequest {
    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;
    @NotNull(message = "Payment method cannot be null")
    private String paymentMethod;
    @NotNull(message = "BookingId cannot be null")
    private UUID bookingId;
    @NotNull(message = "UserId cannot be null")
    private UUID userId;
}
