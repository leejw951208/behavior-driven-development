package com.example.behavior_driven_development.repository;

import com.example.behavior_driven_development.domain.InventorySave;
import com.example.behavior_driven_development.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.persistence.inventory.repository.InventoryEntityJpaRepository;
import com.example.behavior_driven_development.persistence.inventory.repository.InventoryEntityRepository;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.mapper.InventoryMapper;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class InventoryRepositoryImpl implements InventoryRepository {
    private final InventoryEntityJpaRepository inventoryEntityJpaRepository;
    private final InventoryEntityRepository inventoryEntityRepository;
    private final InventoryMapper inventoryMapper;
    private final PerformanceMapper performanceMapper;

    @Override
    public Inventory findInventory(long performanceId, LocalDate reservationDate) {
        InventoryEntity findInventoryEntity = inventoryEntityRepository.findInventory(performanceId, reservationDate)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "공연 좌석 정보를 찾을 수 없습니다."));
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
