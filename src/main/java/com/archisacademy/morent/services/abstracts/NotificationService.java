package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.NotificationRequest;
import com.archisacademy.morent.dtos.responses.NotificationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificationService {
    String sendMessage(String message);
    ResponseEntity<List<NotificationRequest>> getNotificationsForUser(Long userId);
    NotificationResponse createNotification(NotificationRequest notificationRequest);
    NotificationResponse markAsRead(Long notificationId);
}
