package com.example.behavior_driven_development.reservation.persistence.inventory.repository;

import com.example.behavior_driven_development.reservation.persistence.inventory.InventoryEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface InventoryEntityRepository {
    Optional<InventoryEntity> findInventory(long performanceId, LocalDate reservationDate, int quantity);
}
