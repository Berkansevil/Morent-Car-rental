package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.requests.NotificationRequest;
import com.archisacademy.morent.dtos.responses.NotificationResponse;
import com.archisacademy.morent.services.abstracts.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse > createNotification(@RequestBody  NotificationRequest notification) {
        NotificationResponse response = notificationService.createNotification(notification);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{notificationId}/read")
    public ResponseEntity<NotificationResponse> markNotificationRead(@PathVariable Long notificationId) {
        NotificationResponse response=  notificationService.markAsRead(notificationId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("send")
    public ResponseEntity<NotificationResponse>sendNotification(@RequestBody NotificationRequest notification) {
        NotificationResponse notificationResponse=notificationService.createNotification(notification);
        return ResponseEntity.ok(notificationResponse);
    }
}
