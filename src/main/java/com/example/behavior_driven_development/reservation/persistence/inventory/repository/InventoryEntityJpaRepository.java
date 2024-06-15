package com.example.behavior_driven_development.reservation.persistence.inventory.repository;

import com.example.behavior_driven_development.reservation.persistence.inventory.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryEntityJpaRepository extends JpaRepository<InventoryEntity, Long> {
}
