package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String message;

    public UserResponse(UUID userId, String username, String email, String phoneNumber, boolean active) {
    }

}
