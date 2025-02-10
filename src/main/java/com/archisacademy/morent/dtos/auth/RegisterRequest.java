package com.archisacademy.morent.dtos.auth;

import com.archisacademy.morent.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    private String username;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters.")
    private String password;

    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number must be valid. (e.g., +1234567890 or 1234567890)")
    private String phoneNumber;

    @NotBlank(message = "Address is required.")
    @Size(max = 255, message = "Address cannot exceed 255 characters.")
    private String address;

    private Set<Role> roles;
}
