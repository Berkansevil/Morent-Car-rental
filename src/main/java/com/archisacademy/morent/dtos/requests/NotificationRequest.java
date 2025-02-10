package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class NotificationRequest {
    @NotNull(message = "userId can not be null!!")
    private UUID userId;
    @NotNull(message = "Message can not be null")
    private String message;

    private LocalDateTime dateSent;

    private boolean read;

    public NotificationRequest(UUID userId, String message) {
        this.userId = userId;
        this.message = message;
        this.dateSent = LocalDateTime.now();
        this.read = false;
    }
}
