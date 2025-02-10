package com.archisacademy.morent.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentHistoryRespone {
    private UUID paymentId;
    private UUID bookingId;
    private UUID userId;
    private BigDecimal amount;
    private String paymentMethod;

}
