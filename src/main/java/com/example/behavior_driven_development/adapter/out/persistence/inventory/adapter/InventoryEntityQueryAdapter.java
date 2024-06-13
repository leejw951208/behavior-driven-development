package com.example.behavior_driven_development.adapter.out.persistence.inventory.adapter;

import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.inventory.repository.InventoryEntityRepository;
import com.example.behavior_driven_development.application.port.out.InventoryFindOutPort;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class InventoryEntityQueryAdapter implements InventoryFindOutPort {
    private final InventoryEntityRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public Inventory findInventory(long performanceId, LocalDate reservationDate) {
        InventoryEntity findInventoryEntity = inventoryRepository.findByPerformanceIdAndReservationDate(performanceId, reservationDate)
                .orElseThrow(() -> new NoSuchElementException("요청 데이터를 찾을 수 없습니다."));
        return inventoryMapper.toDomain(findInventoryEntity);
    }
}
