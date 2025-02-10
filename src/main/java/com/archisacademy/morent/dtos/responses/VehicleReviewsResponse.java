package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VehicleReviewsResponse {

    private UUID reviewId;
    private UUID userId;
    private String rating;
    private String reviewText;
    private Timestamp createdAt;
}
