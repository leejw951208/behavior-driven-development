package com.example.behavior_driven_development.adapter.out.persistence.inventory.repository;

import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface InventoryEntityRepository {
    Optional<InventoryEntity> findByPerformanceIdAndReservationDate(long performanceId, LocalDate reservationDate);
}
