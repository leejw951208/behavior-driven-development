package com.example.behavior_driven_development.performance.unit;

import com.example.behavior_driven_development.base.BaseUnitDatabaseTest;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.persistence.performance.repository.PerformanceEntityJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class PerformanceJpaTest extends BaseUnitDatabaseTest {
    @Autowired
    private PerformanceEntityJpaRepository performanceEntityJpaRepository;

    private static PerformanceEntity savedPerformanceEntity;

    @Test
    @DisplayName("JPA 단위 테스트: 공연 정보 저장")
    public void savePerformance() {
        // given
        String performanceName = "홍길동전";
        PerformanceEntity createdPerformanceEntity = PerformanceEntity.builder().performanceName(performanceName).build();

        // when
        savedPerformanceEntity = performanceEntityJpaRepository.save(createdPerformanceEntity);

        // then
        assertNotNull(savedPerformanceEntity);
        assertNotNull(savedPerformanceEntity.getId());
        assertEquals(savedPerformanceEntity.getPerformanceName(), createdPerformanceEntity.getPerformanceName());
    }
}
