package com.example.behavior_driven_development.reservation.repository;

import com.example.behavior_driven_development.reservation.domain.InventorySave;
import com.example.behavior_driven_development.reservation.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.reservation.persistence.inventory.repository.InventoryEntityJpaRepository;
import com.example.behavior_driven_development.reservation.persistence.inventory.repository.InventoryEntityRepository;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.domain.Inventory;
import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.mapper.InventoryMapper;
import com.example.behavior_driven_development.reservation.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InventoryRepositoryImpl implements InventoryRepository {
    private final InventoryEntityJpaRepository inventoryEntityJpaRepository;
    private final InventoryEntityRepository inventoryEntityRepository;
    private final InventoryMapper inventoryMapper;
    private final PerformanceMapper performanceMapper;

    @Override
    public Inventory findInventory(long performanceId, LocalDate reservationDate, int quantity) {
        InventoryEntity findInventoryEntity = inventoryEntityRepository.findInventory(performanceId, reservationDate, quantity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 예약이 마감되었습니다."));
        return inventoryMapper.toDomain(findInventoryEntity);
    }

    @Override
    public void saveAll(Performance performance, List<InventorySave> inventorySaves) {
        PerformanceEntity createdPerformanceEntity = performanceMapper.toEntity(performance);
        List<InventoryEntity> createdInventoryEntities = inventoryMapper.toEntities(inventorySaves, createdPerformanceEntity);
        inventoryEntityJpaRepository.saveAll(createdInventoryEntities);
    }

    @Override
    public void save(Performance performance, Inventory inventory) {
        PerformanceEntity createdPerformanceEntity = performanceMapper.toEntity(performance);
        InventoryEntity createdInventoryEntity = inventoryMapper.toEntity(inventory, createdPerformanceEntity);
        inventoryEntityJpaRepository.save(createdInventoryEntity);
    }
}
