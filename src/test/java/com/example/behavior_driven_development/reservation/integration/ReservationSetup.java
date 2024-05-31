package com.example.behavior_driven_development.reservation.integration;

import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.inventory.repository.InventoryEntityJpaRepository;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.repository.PerformanceEntityJpaRepository;
import com.example.behavior_driven_development.domain.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class ReservationSetup {
    @Autowired
    private PerformanceEntityJpaRepository performanceEntityJpaRepository;

    @Autowired
    private InventoryEntityJpaRepository inventoryEntityJpaRepository;

    public PerformanceEntity savePerformance(String performanceName) {
        PerformanceEntity performanceEntity = PerformanceEntity.builder()
                .performanceName(performanceName)
                .build();
        return performanceEntityJpaRepository.save(performanceEntity);
    }

    public void saveInventory(PerformanceEntity performanceEntity, LocalDate reservationDate, int quantity) {
        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .performanceEntity(performanceEntity)
                .quantity(quantity)
                .reservationDate(reservationDate)
                .build();
        inventoryEntityJpaRepository.save(inventoryEntity);
    }
}
