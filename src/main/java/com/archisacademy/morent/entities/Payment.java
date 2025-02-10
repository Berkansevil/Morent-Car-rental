package com.archisacademy.morent.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;
    @Column(name = "payment_uuid")
    private UUID paymentId=UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "status", nullable = false)
    private String status = "PENDING";

    @Version
    private Long version=0L;
    
}
