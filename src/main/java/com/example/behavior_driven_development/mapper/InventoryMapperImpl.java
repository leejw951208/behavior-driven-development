package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.domain.InventorySave;
import com.example.behavior_driven_development.dto.InventorySaveRequestDto;
import com.example.behavior_driven_development.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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
    public List<InventoryEntity> toEntities(List<InventorySave> inventorySaves, PerformanceEntity performanceEntity) {
        return inventorySaves.stream()
                .map(inventory -> InventoryEntity.builder()
                        .performanceEntity(performanceEntity)
                        .quantity(inventory.getQuantity())
                        .reservationDate(inventory.getReservationDate())
                        .build()
                )
                .toList();
    }

    @Override
    public Inventory toDomain(InventoryEntity inventoryEntity) {
        return Inventory.builder()
                .inventoryId(inventoryEntity.getId())
                .quantity(inventoryEntity.getQuantity())
                .reservationDate(inventoryEntity.getReservationDate())
                .build();
    }

    @Override
    public List<InventorySave> toDomains(List<InventorySaveRequestDto> requestDtos) {
        return requestDtos.stream()
                .map(requestDto -> new InventorySave(requestDto.quantity(), requestDto.reservationDate()))
                .toList();
    }
}
