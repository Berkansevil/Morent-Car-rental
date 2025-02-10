package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class ConfirmPaymentRequest {
    @NotNull(message = "Payment ID cannot be null")
    private UUID paymentId;

    @NotNull(message = "Status cannot be null")
    private String status;
}
