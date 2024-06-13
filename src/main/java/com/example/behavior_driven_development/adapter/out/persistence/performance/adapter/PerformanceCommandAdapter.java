package com.example.behavior_driven_development.adapter.out.persistence.performance.adapter;

import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.repository.PerformanceEntityJpaRepository;
import com.example.behavior_driven_development.application.port.out.PerformanceSaveOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceCommandAdapter implements PerformanceSaveOutPort {
    private final PerformanceEntityJpaRepository performanceEntityJpaRepository;

    @Override
    public void save(String performanceName) {
        PerformanceEntity performanceEntity = PerformanceEntity.builder()
                .performanceName(performanceName)
                .build();
        performanceEntityJpaRepository.save(performanceEntity);
    }
}
