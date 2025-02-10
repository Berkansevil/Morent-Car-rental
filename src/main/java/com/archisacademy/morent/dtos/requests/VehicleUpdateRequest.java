package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleUpdateRequest {

    private String make;

    private String model;

    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    @Max(value = 2100, message = "Year must be less than or equal to 2100")
    private int year;

    private String location;

    @Positive(message = "Price per day must be a positive value")
    private BigDecimal pricePerDay;

    private List<String> features;

    private boolean availability;

}
