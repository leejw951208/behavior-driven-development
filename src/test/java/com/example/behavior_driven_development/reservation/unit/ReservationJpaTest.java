package com.example.behavior_driven_development.reservation.unit;

import com.example.behavior_driven_development.base.BaseUnitDatabaseTest;
import com.example.behavior_driven_development.reservation.persistence.inventory.repository.InventoryEntityJpaRepository;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.persistence.performance.repository.PerformanceEntityJpaRepository;
import com.example.behavior_driven_development.reservation.persistence.performance.repository.PerformanceEntityRepository;
import com.example.behavior_driven_development.reservation.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.reservation.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.reservation.persistence.reservation.ReservationEntityJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationJpaTest extends BaseUnitDatabaseTest {
    @Autowired
    private ReservationEntityJpaRepository reservationEntityJpaRepository;

    @Autowired
    private PerformanceEntityJpaRepository performanceEntityJpaRepository;

    @Autowired
    private PerformanceEntityRepository performanceEntityRepository;

    @Autowired
    private InventoryEntityJpaRepository inventoryEntityJpaRepository;

    private static final LocalDate date = LocalDate.of(2024, 5, 29);

    @Test
    @DisplayName("JPA 단위 테스트: 공연 예약")
    public void reservePerformance() {
        // given
        PerformanceEntity performanceEntity = PerformanceEntity.builder().performanceName("홍길동전").build();
        PerformanceEntity savedPerformanceEntity = performanceEntityJpaRepository.save(performanceEntity);

        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .performanceEntity(savedPerformanceEntity)
                .quantity(1)
                .reservationDate(date)
                .build();
        inventoryEntityJpaRepository.save(inventoryEntity);

        // when
        PerformanceEntity findPerformance = performanceEntityRepository.findPerformance(savedPerformanceEntity.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "공연 정보를 찾을 수 없습니다."));

        ReservationEntity createdPerformanceReserve = ReservationEntity.builder()
                .performanceEntity(findPerformance)
                .customerName("홍길동")
                .reservationDate(date)
                .build();

        ReservationEntity savedPerformanceReserveEntity = reservationEntityJpaRepository.save(createdPerformanceReserve);

        // then
        assertNotNull(savedPerformanceReserveEntity);
        assertEquals(createdPerformanceReserve, savedPerformanceReserveEntity);
    }
}
