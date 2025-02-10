package com.archisacademy.morent.dtos.requests;
import lombok.Data;

@Data
public class CreateDiscountRequest {
    private String code;
    private Integer discountPercentage;
    private String expiryDate;
}
