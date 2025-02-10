package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class BookingStatusRequest {

    @NotNull(message = "VehicleId cannot be null")
    private UUID vehicleId;

    @NotNull(message = "Start date cannot be null")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Start date must be in the format YYYY-MM-DD")
    private String startDate;

    @NotNull(message = "End date cannot be null")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "End date must be in the format YYYY-MM-DD")
    private String endDate;
}
