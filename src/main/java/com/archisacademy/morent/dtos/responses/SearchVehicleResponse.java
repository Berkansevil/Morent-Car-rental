package com.archisacademy.morent.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SearchVehicleResponse {
    private UUID vehicleId;
    private String make;
    private String model;
    private int year;
    private String location;
    private BigDecimal pricePerDay;
    private List<String> features;
    private boolean available;

    public SearchVehicleResponse(UUID vehicleId, String make, String model, int year, String location, BigDecimal pricePerDay, List<String> features, boolean availability) {

    }
}
