package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapperImpl implements InventoryMapper {
    @Override
    public InventoryEntity toEntity(Inventory inventory, PerformanceEntity performanceEntity) {
        return InventoryEntity.builder()
                .id(inventory.getInventoryId())
                .performanceEntity(performanceEntity)
                .quantity(inventory.getQuantity())
                .reservationDate(inventory.getReservationDate())
                .build();
    }

    @Override
    public Inventory toDomain(InventoryEntity inventoryEntity) {
        return Inventory.builder()
                .inventoryId(inventoryEntity.getId())
                .quantity(inventoryEntity.getQuantity())
                .reservationDate(inventoryEntity.getReservationDate())
                .build();
    }
}
