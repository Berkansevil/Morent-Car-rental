package com.archisacademy.morent.repositories;

import com.archisacademy.morent.entities.Booking;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.vehicle.vehicleId = :vehicleId " +
            "AND b.startDate < :endDate AND b.endDate > :startDate")
    List<Booking> findConflictingBookings(@Param("vehicleId") UUID vehicleId,
                                          @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);

    Optional<Booking> findByBookingIdEquals(UUID bookingId);

    Optional<Booking> findByBookingId(UUID bookingId);

}
