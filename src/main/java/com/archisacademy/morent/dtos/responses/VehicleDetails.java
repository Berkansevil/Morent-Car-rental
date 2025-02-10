package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetails {
    private UUID vehicleId;
    private String make;
    private String model;
    private int year;
    private double pricePerDay;
    private List<String> features;
    private String termsAndConditions;
    private boolean availability;
}
