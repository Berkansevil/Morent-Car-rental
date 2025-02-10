package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    @NotBlank(message = "user_id can not be empty!!!")
    private UUID userId;

    @NotBlank(message = "vehicle_id can not be empty!!!")
    private UUID vehicleId;

    @NotBlank(message = "rating can not be empty!!!")
    private int rating;

    @NotBlank(message = "review_text can not be empty!!!")
    private String reviewText;
}
