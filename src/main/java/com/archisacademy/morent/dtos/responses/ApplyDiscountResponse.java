package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDiscountResponse {
    private String message;
    private BigDecimal newTotalAmount;
}
