package com.archisacademy.morent.repositories;


import com.archisacademy.morent.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBookingId(Long bookingId);

    Optional<Payment> findByBooking_User_UserIdEquals(UUID userId);

    Optional<Payment> findByPaymentId(UUID paymentId);
}
