package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.BitSet;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailsResponse {

    private UUID bookingId;
    private UUID userId;
    private UUID vehicleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalAmount;
    private Boolean available;
    private String status;
}
