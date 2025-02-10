package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "Kullanıcı adı boş bırakılamaz!!!")
    private String username;

    @NotBlank(message = "Telefon numarası boş bırakılamaz!!!")
    private String phoneNumber;

    @NotBlank(message = "Adres boş bırakılamaz!!!")
    private String address;
}
