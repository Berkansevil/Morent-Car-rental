package com.archisacademy.morent.repositories;


import com.archisacademy.morent.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DiscountRepository extends JpaRepository<Discount, UUID> {
    boolean existsByCode(String code);
    Optional<Discount> findByCode(String code);
    List<Discount> findByExpiryDateAfter(LocalDate date);

}
