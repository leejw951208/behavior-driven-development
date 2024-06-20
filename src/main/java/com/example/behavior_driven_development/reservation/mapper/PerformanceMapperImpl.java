package com.example.behavior_driven_development.reservation.mapper;

import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.domain.Performance;
import org.springframework.stereotype.Component;

@Component
public class PerformanceMapperImpl implements PerformanceMapper {
    @Override
    public Performance toDomain(PerformanceEntity performanceEntity) {
        return Performance.builder()
                .id(performanceEntity.getId())
                .performanceName(performanceEntity.getPerformanceName())
                .createdDate(performanceEntity.getCreatedDate())
                .build();
    }

    @Override
    public PerformanceEntity toEntity(String performanceName) {
        return PerformanceEntity.builder()
                .performanceName(performanceName)
                .build();
    }

    @Override
    public PerformanceEntity toEntity(Performance performance) {
        return PerformanceEntity.builder()
                .id(performance.getId())
                .performanceName(performance.getPerformanceName())
                .createdDate(performance.getCreatedDate())
                .build();
    }
}
