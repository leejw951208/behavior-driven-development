package com.example.behavior_driven_development.performance;

import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.persistence.performance.repository.PerformanceEntityJpaRepository;
import com.example.behavior_driven_development.reservation.persistence.performance.repository.PerformanceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerformanceTestSetup {
    @Autowired
    private PerformanceEntityJpaRepository performanceEntityJpaRepository;

    public PerformanceEntity save(String performanceName) {
        PerformanceEntity newPerformanceEntity = PerformanceEntity.builder()
                .performanceName(performanceName)
                .build();
        return performanceEntityJpaRepository.save(newPerformanceEntity);
    }
}
