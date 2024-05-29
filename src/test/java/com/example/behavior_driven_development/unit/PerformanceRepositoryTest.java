package com.example.behavior_driven_development.unit;

import com.example.behavior_driven_development.adapter.out.persistence.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.PerformanceInventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.PerformanceReserveEntity;
import com.example.behavior_driven_development.adapter.out.persistence.repository.*;
import com.example.behavior_driven_development.base.BaseUnitTest;
import com.example.behavior_driven_development.config.JpaAuditingConfig;
import com.example.behavior_driven_development.config.QueryDslConfig;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@Import(value = {JpaAuditingConfig.class, QueryDslConfig.class})
public class PerformanceRepositoryTest extends BaseUnitTest {
    @Autowired
    private PerformanceReserveEntityJpaRepository performanceReserveEntityJpaRepository;

    @Autowired
    private PerformanceEntityJpaRepository performanceEntityJpaRepository;

    @Autowired
    private PerformanceEntityRepository performanceEntityRepository;

    @Autowired
    private PerformanceInventoryEntityJpaRepository performanceInventoryEntityJpaRepository;

    private static final LocalDate date = LocalDate.of(2024, 5, 29);

    @BeforeEach
    public void setup() {
        PerformanceEntity performanceEntity1 = PerformanceEntity.builder().performanceName("홍길동전").build();
        PerformanceEntity performanceEntity2 = PerformanceEntity.builder().performanceName("춘향전").build();
        PerformanceEntity performanceEntity3 = PerformanceEntity.builder().performanceName("레미제라블").build();

        performanceEntityJpaRepository.saveAll(List.of(performanceEntity1, performanceEntity2, performanceEntity3));

        PerformanceInventoryEntity inventoryEntity1 = PerformanceInventoryEntity.builder().performanceEntity(performanceEntity1).quantity(2).reservableDate(date).build();
        PerformanceInventoryEntity inventoryEntity2 = PerformanceInventoryEntity.builder().performanceEntity(performanceEntity2).quantity(2).reservableDate(date).build();
        PerformanceInventoryEntity inventoryEntity3 = PerformanceInventoryEntity.builder().performanceEntity(performanceEntity3).quantity(2).reservableDate(date).build();

        performanceInventoryEntityJpaRepository.saveAll(List.of(inventoryEntity1, inventoryEntity2, inventoryEntity3));
    }

    @Test
    @DisplayName("리포지토리 단위 테스트: 공연 예약")
    public void reservePerformance() {
        // given

        // when

        // then
    }
}
