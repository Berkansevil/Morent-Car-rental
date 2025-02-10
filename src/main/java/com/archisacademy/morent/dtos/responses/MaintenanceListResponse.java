package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceListResponse {

    private UUID maintenance;
    private LocalDate startDate;
    private LocalDate endDate;
    private String details;
}
