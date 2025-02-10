package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookingSummaryResponse {
    private String carModel;
    private LocalDate startDate;
    private LocalDate endDate;
}

