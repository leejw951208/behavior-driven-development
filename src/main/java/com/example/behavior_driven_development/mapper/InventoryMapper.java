package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Performance;

public interface InventoryMapper {
    InventoryEntity toEntity(Inventory inventory, PerformanceEntity performanceEntity);
    Inventory toDomain(InventoryEntity inventoryEntity);
}
