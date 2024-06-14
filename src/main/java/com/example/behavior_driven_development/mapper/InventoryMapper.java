package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.domain.InventorySave;
import com.example.behavior_driven_development.dto.InventorySaveRequestDto;
import com.example.behavior_driven_development.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;

import java.time.LocalDate;
import java.util.List;

public interface InventoryMapper {
    InventoryEntity toEntity(Inventory inventory, PerformanceEntity performanceEntity);
    List<InventoryEntity> toEntities(List<InventorySave> inventorySaves, PerformanceEntity performanceEntity);
    Inventory toDomain(InventoryEntity inventoryEntity);
    List<InventorySave> toDomains(List<InventorySaveRequestDto> requestDtos);
}
