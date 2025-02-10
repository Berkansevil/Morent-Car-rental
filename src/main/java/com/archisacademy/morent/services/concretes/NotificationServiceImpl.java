package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.ModelMappper.ModelMapperServiceImpl;
import com.archisacademy.morent.dtos.requests.NotificationRequest;
import com.archisacademy.morent.dtos.responses.NotificationResponse;
import com.archisacademy.morent.entities.Notification;
import com.archisacademy.morent.entities.User;
import com.archisacademy.morent.repositories.NotificationRepository;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.services.abstracts.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final NotificationRepository notificationRepository;
    private final ModelMapperServiceImpl modelMapperService;
    private final UserRepository userRepository;

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    @Override
    public String sendMessage(String message) {
        System.out.println("Sending Message to Kafka Broker: " + message);
        kafkaTemplate.send(topicName, message);
        return "Message sent successfully";
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "group-id")
    public void receiveMessage(String message) {
        System.out.println("Received Message from Kafka Broker: " + message);
    }


    @Override
    public ResponseEntity<List<NotificationRequest>> getNotificationsForUser(Long userId) {
       List<Notification> notifications=notificationRepository.findByUserId(userId);
       List<NotificationRequest> responses=notifications.stream()
               .map(notification -> modelMapperService.request().map(notification, NotificationRequest.class))
               .collect(Collectors.toList());
       return new ResponseEntity<>(responses, HttpStatus.OK);
    }


    @Override
    @Transactional
    public NotificationResponse createNotification(NotificationRequest notificationRequest) {
        Notification notification = new Notification();
        notification.setMessage(notificationRequest.getMessage());
        notification.setDateSent(notificationRequest.getDateSent()!=null ? notificationRequest.getDateSent():LocalDateTime.now());
        notification.setRead(false);
        User user = userRepository.findByUserId(notificationRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        notification.setUser(user);
        notificationRepository.save(notification);
        kafkaTemplate.send(topicName, notificationRequest.getMessage());
        return new NotificationResponse("Notification created successfully");
    }

    @Override
    public NotificationResponse markAsRead(Long notificationId) {
        Notification notification=notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        if(notification.isRead()){
            return new NotificationResponse("Notification already marked as read");
        }
        notification.setRead(true);
        notificationRepository.save(notification);
        kafkaTemplate.send("notification-topic","Notification with ID " + notificationId + " marked as read");
        return new NotificationResponse("Notification marked as read");
    }

}

