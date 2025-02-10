package com.archisacademy.morent.dtos.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequest {

    @NotBlank(message = "Vehicle can not be empty!!!")
    private UUID vehicleId;

    @NotBlank(message = "Start date can not be empty!!!")
    private LocalDate startDate;

    @NotBlank(message = "End date can not be empty!!!")
    private LocalDate endDate;

    @NotBlank(message = "Details should be disclosed")
    private String details;
}
