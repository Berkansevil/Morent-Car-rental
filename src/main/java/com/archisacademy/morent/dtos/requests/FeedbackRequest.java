package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {

    @NotNull(message = "userId can not be empty!")
    private UUID userId;

    @NotNull(message = "Feedback text can not be empty!")
    private String feedbackText;

}
