package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ActiveDiscountResponse {
    private UUID discountId;
    private String code;
    private int discountPercentage;
    private LocalDate expiryDate;
}