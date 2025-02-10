package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

    @NotBlank(message = "Make can not be empty")
    private String make;

    @NotBlank(message = "Model can not be empty")
    private String model;

    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    @Max(value = 2100, message = "Year must be less than or equal to 2100")
    private int year;

    @NotBlank(message = "Location cannot be empty")
    private String location;
    @NotBlank(message = "Type cannot be empty")
    private String type;
    @NotNull(message = "Price per day cannot be null")
    @Positive(message = "Price per day must be a positive value")
    private BigDecimal pricePerDay;

    @NotEmpty(message = "Features cannot be empty")
    private List<String> features;

    private boolean availability;
}
