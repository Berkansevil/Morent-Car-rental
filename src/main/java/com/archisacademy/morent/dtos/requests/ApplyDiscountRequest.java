package com.archisacademy.morent.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDiscountRequest {
    private UUID bookingId;
    private String code;
}
