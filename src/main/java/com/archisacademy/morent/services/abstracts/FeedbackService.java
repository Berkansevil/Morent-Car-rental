package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.FeedbackRequest;
import com.archisacademy.morent.dtos.responses.FeedbackAdminResponse;
import com.archisacademy.morent.dtos.responses.FeedbackResponse;

import java.util.List;

public interface FeedbackService {

    FeedbackResponse save(FeedbackRequest feedbackRequest);

    List<FeedbackAdminResponse> findAll();

}
