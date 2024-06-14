package com.example.behavior_driven_development.inventory.unit;

import com.example.behavior_driven_development.base.BaseUnitDatabaseTest;
import com.example.behavior_driven_development.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.persistence.inventory.repository.InventoryEntityJpaRepository;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.persistence.performance.repository.PerformanceEntityJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InventoryJpaTest extends BaseUnitDatabaseTest {
    @Autowired
    private PerformanceEntityJpaRepository performanceEntityJpaRepository;

    @Autowired
    private InventoryEntityJpaRepository inventoryEntityJpaRepository;

    private PerformanceEntity savedPerformanceEntity;
    @BeforeEach
    public void beforeEach() {
        String performanceName = "홍길동전";
        PerformanceEntity performanceEntity = PerformanceEntity.builder().performanceName(performanceName).build();
        savedPerformanceEntity = performanceEntityJpaRepository.save(performanceEntity);
    }

    @Test
    @DisplayName("JPA 단위 테스트: 공연 좌석 정보 저장")
    public void saveInventory() {
        // given
        int quantity = 1;
        LocalDate reservationDate = LocalDate.now();
        InventoryEntity inventoryEntity = InventoryEntity.builder().performanceEntity(savedPerformanceEntity).quantity(quantity).reservationDate(reservationDate).build();

        // when
        InventoryEntity savedInventoryEntity = inventoryEntityJpaRepository.save(inventoryEntity);

        // then
        assertNotNull(savedInventoryEntity);
        assertEquals(savedInventoryEntity.getPerformanceEntity(), savedPerformanceEntity);
        assertEquals(savedInventoryEntity.getQuantity(), quantity);
        assertEquals(savedInventoryEntity.getReservationDate(), reservationDate);
    }
}
