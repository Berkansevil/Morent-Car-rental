package com.archisacademy.morent.repositories;

import com.archisacademy.morent.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

}
