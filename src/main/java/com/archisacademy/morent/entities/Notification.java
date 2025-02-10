package com.archisacademy.morent.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="notification" )
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notification_id")
    private UUID notificationId=UUID.randomUUID();

    @Column(name = "message",nullable = false, length = 100)
    private String message;

    @Column(name = "date_sent" )
    private LocalDateTime dateSent;

    @Column(name ="read")
    private boolean read;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
