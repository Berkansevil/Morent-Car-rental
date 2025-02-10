package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.ModelMappper.ModelMapperServiceImpl;
import com.archisacademy.morent.dtos.auth.LoginRequest;
import com.archisacademy.morent.dtos.auth.LoginResponse;
import com.archisacademy.morent.dtos.auth.RegisterRequest;
import com.archisacademy.morent.dtos.auth.RegisterResponse;
import com.archisacademy.morent.dtos.responses.UserResponse;
import com.archisacademy.morent.dtos.responses.BookingDetailsResponse;
import com.archisacademy.morent.entities.Role;
import com.archisacademy.morent.entities.User;
import com.archisacademy.morent.jwt.JwtService;
import com.archisacademy.morent.repositories.BookingRepository;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.services.abstracts.AdminService;
import com.archisacademy.morent.services.abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ModelMapperServiceImpl modelMapperService;
    private final BookingRepository bookingRepository;

    @Override
    public RegisterResponse registerAdmin(RegisterRequest registerRequest) {
        registerRequest.setRoles(Set.of(Role.ROLE_ADMIN));
        return authService.register(registerRequest);

    }

    @Override
    public LoginResponse adminLogin(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            User user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtService.generateToken(user);
            return new LoginResponse("Login successful", token);

        } catch (Exception e) {
            throw new RuntimeException("Invalid username and password " + e.getMessage());
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(
                        user.getUserId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.isActive()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDetailsResponse> getAllBookingDetails() {
        return bookingRepository.findAll().stream()
                .map(booking -> modelMapperService.request().map(booking,BookingDetailsResponse.class))
                .collect(Collectors.toList());
    }

}
