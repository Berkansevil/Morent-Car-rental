package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.requests.ReviewRequest;
import com.archisacademy.morent.dtos.requests.ReviewUpdateRequest;
import com.archisacademy.morent.dtos.responses.ReviewResponse;
import com.archisacademy.morent.services.abstracts.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> submitReview(@RequestBody ReviewRequest reviewRequest) {
        ReviewResponse response = reviewService.submitReview(reviewRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long reviewId, @RequestBody ReviewUpdateRequest reviewUpdateRequest) {
        ReviewResponse response = reviewService.updateReview(reviewId, reviewUpdateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> deleteReview(@PathVariable Long reviewId) {
        ReviewResponse response = reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(response);
    }


}
