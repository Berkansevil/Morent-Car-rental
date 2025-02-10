package com.archisacademy.morent.controllers;

import com.archisacademy.morent.ApiResponse.ApiResponse;
import com.archisacademy.morent.dtos.requests.FeedbackRequest;
import com.archisacademy.morent.dtos.responses.FeedbackResponse;
import com.archisacademy.morent.services.abstracts.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody FeedbackRequest feedbackRequest) {
        FeedbackResponse response = feedbackService.save(feedbackRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
