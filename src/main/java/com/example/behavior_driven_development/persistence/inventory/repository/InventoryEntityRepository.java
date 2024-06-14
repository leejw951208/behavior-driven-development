package com.example.behavior_driven_development.persistence.inventory.repository;

import com.example.behavior_driven_development.persistence.inventory.InventoryEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface InventoryEntityRepository {
    Optional<InventoryEntity> findInventory(long performanceId, LocalDate reservationDate);
}
