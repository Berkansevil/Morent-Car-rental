package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleFilterResponse {

    private UUID vehicleId;
    private String make;
    private String model;
    private int year;
    private String location;
    private BigDecimal pricePerDay;
    private List<String> features;
    private boolean availability;
}
