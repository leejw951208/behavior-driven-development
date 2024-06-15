package com.example.behavior_driven_development.reservation.mapper;

import com.example.behavior_driven_development.reservation.domain.InventorySave;
import com.example.behavior_driven_development.reservation.dto.InventorySaveRequestDto;
import com.example.behavior_driven_development.reservation.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.domain.Inventory;

import java.util.List;

public interface InventoryMapper {
    InventoryEntity toEntity(Inventory inventory, PerformanceEntity performanceEntity);
    List<InventoryEntity> toEntities(List<InventorySave> inventorySaves, PerformanceEntity performanceEntity);
    Inventory toDomain(InventoryEntity inventoryEntity);
    List<InventorySave> toDomains(List<InventorySaveRequestDto> requestDtos);
}
