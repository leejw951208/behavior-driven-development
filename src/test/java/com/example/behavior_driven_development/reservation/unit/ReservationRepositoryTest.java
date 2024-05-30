package com.example.behavior_driven_development.reservation.unit;

import com.example.behavior_driven_development.adapter.out.persistence.inventory.repository.InventoryEntityJpaRepository;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.repository.PerformanceEntityJpaRepository;
import com.example.behavior_driven_development.adapter.out.persistence.performance.repository.PerformanceEntityRepository;
import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntityJpaRepository;
import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.config.JpaAuditingConfig;
import com.example.behavior_driven_development.config.TestQueryDslConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@DataJpaTest
@Import(value = {JpaAuditingConfig.class, TestQueryDslConfig.class})
public class ReservationRepositoryTest extends BaseTest {
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
    @DisplayName("리포지토리 단위 테스트: 공연 예약")
    public void reservePerformance() {
        // given
        PerformanceEntity performanceEntity = PerformanceEntity.builder().performanceName("홍길동전").build();
        performanceEntityJpaRepository.save(performanceEntity);

        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .performanceEntity(performanceEntity)
                .quantity(1)
                .reservationDate(date)
                .build();
        inventoryEntityJpaRepository.save(inventoryEntity);

        // when
        PerformanceEntity findPerformance = performanceEntityRepository.findById(1L)
                .orElseThrow(() -> new NoSuchElementException("공연 정보를 찾을 수 없습니다."));

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

    @Test
    @DisplayName("리포지토리 단위 테스트: 공연 예약 실패")
    public void reservePerformanceFailed() {
        // given
        PerformanceEntity savedPerformanceEntity = performanceEntityJpaRepository.save(PerformanceEntity.builder().performanceName("홍길동전").build());

        InventoryEntity createdInventoryEntity = InventoryEntity.builder()
                .performanceEntity(savedPerformanceEntity)
                .quantity(0)
                .reservationDate(date)
                .build();
        inventoryEntityJpaRepository.save(createdInventoryEntity);

        // when
        Throwable throwable = catchThrowable(() -> performanceEntityRepository.findByIdAndReservationDate(1L, date));

        // then
    }
}
