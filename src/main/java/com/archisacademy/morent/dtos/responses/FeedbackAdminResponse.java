package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackAdminResponse {

    private UUID feedbackId;

    private UUID userId;

    private String feedbackText;

    private Timestamp dateSubmitted;

}
