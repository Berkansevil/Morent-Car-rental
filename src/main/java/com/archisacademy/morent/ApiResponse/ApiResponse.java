package com.archisacademy.morent.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@Data
@NoArgsConstructor
public class ApiResponse<T> {

    private Boolean success;
    private String message;
    private T object;

    public ResponseEntity<?> getResponseJson(ApiResponse apiResponse) {
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

    public ApiResponse(Boolean success, String message, T object) {
        this.success = success;
        this.message = message;
        this.object = object;
    }

}