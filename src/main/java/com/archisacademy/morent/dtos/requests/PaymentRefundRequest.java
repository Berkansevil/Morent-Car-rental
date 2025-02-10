package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRefundRequest {

    @NotNull(message = "Booking ID must be specified!")
    private UUID bookingId;

    @NotNull(message = "Entering the amount is mandatory!")
    private BigDecimal amount;

}
