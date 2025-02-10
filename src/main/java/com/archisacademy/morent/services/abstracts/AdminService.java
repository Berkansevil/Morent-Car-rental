package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.auth.LoginRequest;
import com.archisacademy.morent.dtos.auth.LoginResponse;
import com.archisacademy.morent.dtos.auth.RegisterRequest;
import com.archisacademy.morent.dtos.auth.RegisterResponse;
import com.archisacademy.morent.dtos.responses.UserResponse;
import com.archisacademy.morent.dtos.responses.BookingDetailsResponse;

import java.util.List;

public interface AdminService {
    RegisterResponse registerAdmin(RegisterRequest registerRequest);
    LoginResponse adminLogin(LoginRequest loginRequest);
    List<BookingDetailsResponse> getAllBookingDetails();
    List<UserResponse> getAllUsers();
}
