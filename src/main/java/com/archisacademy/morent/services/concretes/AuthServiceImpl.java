package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.ModelMappper.ModelMapperServiceImpl;
import com.archisacademy.morent.dtos.auth.LoginRequest;
import com.archisacademy.morent.dtos.auth.LoginResponse;
import com.archisacademy.morent.dtos.auth.RegisterRequest;
import com.archisacademy.morent.dtos.auth.RegisterResponse;
import com.archisacademy.morent.entities.Role;
import com.archisacademy.morent.entities.User;
import com.archisacademy.morent.exceptions.UserAlreadyExistsException;
import com.archisacademy.morent.jwt.JwtService;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.services.abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;

    @Override

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("The email '" + request.getEmail() + "' is already registered.");
        }

        Set<Role> userRoles = request.getRoles();
        if (userRoles == null || userRoles.isEmpty()) {
            userRoles = Set.of(Role.ROLE_USER);
        }

        User user = modelMapper.map(request,User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserId(UUID.randomUUID());
        user.setActive(true);
        user.setRoles(userRoles);

        User savedUser = userRepository.save(user);
        return new RegisterResponse("User registered successfully", savedUser.getUserId());
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(auth);
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            String token = jwtService.generateToken(user);
            return new LoginResponse("Login successfully", token);

        } catch (Exception e) {
            return new LoginResponse("Username or password is incorrect", null);
        }
    }
}
