package com.archisacademy.morent.repositories;

import com.archisacademy.morent.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByVehicleVehicleId(UUID vehicleId);

}
