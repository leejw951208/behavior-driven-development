package com.example.behavior_driven_development.inventory.unit;

import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.reservation.domain.InventorySave;
import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.mapper.InventoryMapper;
import com.example.behavior_driven_development.reservation.mapper.PerformanceMapper;
import com.example.behavior_driven_development.reservation.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.reservation.persistence.inventory.repository.InventoryEntityJpaRepository;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.repository.InventoryRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class InventoryRepositoryTest extends BaseTest {
    @Mock
    private PerformanceMapper performanceMapper;

    @Mock
    private InventoryMapper inventoryMapper;

    @Mock
    private InventoryEntityJpaRepository inventoryEntityJpaRepository;

    @InjectMocks
    private InventoryRepositoryImpl inventoryRepository;

    @Test
    @DisplayName("리포지토리 단위 테스트: 공연 좌석 정보 저장")
    public void saveInventory() {
        // given
        long performanceId = 1L;
        String performanceName = "홍길동전";
        Performance performance = Performance.builder().id(performanceId).performanceName(performanceName).build();
        PerformanceEntity performanceEntity = PerformanceEntity.builder().id(performanceId).performanceName(performanceName).build();

        int quantity1 = 1;
        int quantity2 = 2;
        LocalDate reservationDate1 = LocalDate.of(2024,6, 14);
        LocalDate reservationDate2 = LocalDate.of(2024,6, 15);
        List<InventorySave> inventorySaves = List.of(
                InventorySave.builder().quantity(quantity1).reservationDate(reservationDate1).build(),
                InventorySave.builder().quantity(quantity2).reservationDate(reservationDate2).build()
        );
        List<InventoryEntity> inventoryEntities = List.of(
                InventoryEntity.builder().performanceEntity(performanceEntity).quantity(quantity1).reservationDate(reservationDate1).build(),
                InventoryEntity.builder().performanceEntity(performanceEntity).quantity(quantity2).reservationDate(reservationDate2).build()
        );
        List<InventoryEntity> savedInventoryEntities = List.of(
                InventoryEntity.builder().id(1L).performanceEntity(performanceEntity).quantity(quantity1).reservationDate(reservationDate1).build(),
                InventoryEntity.builder().id(2L).performanceEntity(performanceEntity).quantity(quantity2).reservationDate(reservationDate2).build()
        );

        given(performanceMapper.toEntity(performance)).willReturn(performanceEntity);
        given(inventoryMapper.toEntities(inventorySaves, performanceEntity)).willReturn(inventoryEntities);
        given(inventoryEntityJpaRepository.saveAll(inventoryEntities)).willReturn(savedInventoryEntities);

        // when, then
        inventoryRepository.saveAll(performance, inventorySaves);
    }
}
