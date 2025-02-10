package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.ApiResponse.ApiResponse;
import com.archisacademy.morent.ModelMappper.ModelMapperServiceImpl;
import com.archisacademy.morent.dtos.requests.CreateUserRequest;
import com.archisacademy.morent.dtos.requests.UserUpdateRequest;
import com.archisacademy.morent.dtos.responses.*;
import com.archisacademy.morent.entities.Booking;
import com.archisacademy.morent.entities.User;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.services.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperServiceImpl modelMapperService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    public ApiResponse save(CreateUserRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = modelMapper.map(dto, User.class);
        user.setPassword(encodedPassword);
        user.setRoles(dto.getAuthorities());
        User savedUser = userRepository.save(user);

        return new ApiResponse(true, "İşlem başarılı.", savedUser);
    }

    @Override
    public UserUpdateResponse userUpdate(UUID userId, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("User not found!!!"));
        modelMapper.map(userUpdateRequest, user);
        userRepository.save(user);
        return new UserUpdateResponse("User has been updated successfully!!!");
    }

    @Override
    public UserUpdateResponse updateUserStatus(Long userId) {
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()){
        user.get().isEnabled();
            userRepository.saveAndFlush(user.get());
            return new UserUpdateResponse("User status updated successfully");
        }
        throw new RuntimeException("User not found");
    }

    public ApiResponse getUserBookings(Long userId) {
        User  user = userRepository.findById(userId).orElse(null) ;
        if (user==null){
            return new ApiResponse<>(false,"User not found.",null);
        }
        if (user.getBookings().isEmpty()){
            return new ApiResponse<>(false,"User not found bookings .",null);
        }
        List<BookingDetailsResponse> bookings = user.getBookings().stream().map(booking ->{
            BookingDetailsResponse response = new BookingDetailsResponse();
            response.setBookingId(booking.getBookingId());
            response.setEndDate(booking.getEndDate());
            response.setStatus(booking.getStatus());
            response.setTotalAmount(booking.getTotalAmount());
            response.setUserId(booking.getUser().getUserId());
            response.setVehicleId(booking.getVehicle().getVehicleId());
            return response;
        }).collect(Collectors.toList());
        return new ApiResponse<>(true,"İşlem başarılı.",bookings);
    }

    @Override
    public ProfileRetrievalResponse userProfileRetrieval(UUID userId){
        User user = userRepository.findByUserId(userId).orElseThrow(()-> new RuntimeException("User not found"));
        ProfileRetrievalResponse profileRetrievalResponse = modelMapper.map(user, ProfileRetrievalResponse.class);

        List<BookingDetailsResponse> bookings = user.getBookings().stream().map(booking ->{
            BookingDetailsResponse response = new BookingDetailsResponse();
            response.setBookingId(booking.getBookingId());
            response.setEndDate(booking.getEndDate());
            response.setStatus(booking.getStatus());
            response.setTotalAmount(booking.getTotalAmount());
            response.setUserId(booking.getUser().getUserId());
            response.setVehicleId(booking.getVehicle().getVehicleId());
            return response;
        }).collect(Collectors.toList());

        profileRetrievalResponse.setBookings(bookings);

        return profileRetrievalResponse;
    }

}

