package com.example.behavior_driven_development.adapter.out.persistence.inventory.adapter;

import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.inventory.repository.InventoryEntityJpaRepository;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.application.port.out.InventorySaveOutPort;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.mapper.InventoryMapper;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InventoryEntityCommandAdapter implements InventorySaveOutPort {
    private final InventoryEntityJpaRepository inventoryEntityJpaRepository;
    private final InventoryMapper inventoryMapper;
    private final PerformanceMapper performanceMapper;

    @Override
    public void updateInventory(Inventory inventory, Performance performance) {
        PerformanceEntity performanceEntity = performanceMapper.toEntity(performance);
        InventoryEntity inventoryEntity = inventoryMapper.toEntity(inventory, performanceEntity);
        inventoryEntityJpaRepository.save(inventoryEntity);
    }

    @Override
    public void save(int quantity, Performance performance) {
        PerformanceEntity performanceEntity = performanceMapper.toEntity(performance);
        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .performanceEntity(performanceEntity)
                .reservationDate(performance.getCreatedDate())
                .quantity(quantity)
                .build();
        inventoryEntityJpaRepository.save(inventoryEntity);
    }
}
