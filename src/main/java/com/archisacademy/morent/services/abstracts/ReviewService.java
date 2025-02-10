package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.ReviewRequest;
import com.archisacademy.morent.dtos.requests.ReviewUpdateRequest;
import com.archisacademy.morent.dtos.responses.ReviewResponse;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewResponse submitReview(ReviewRequest reviewRequest);

    ReviewResponse updateReview(Long reviewId, ReviewUpdateRequest reviewUpdateRequest);

    ReviewResponse deleteReview(Long reviewId);


}
