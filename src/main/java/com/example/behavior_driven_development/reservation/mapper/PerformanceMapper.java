package com.example.behavior_driven_development.reservation.mapper;

import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.domain.Performance;

public interface PerformanceMapper {
    Performance toDomain(PerformanceEntity performanceEntity);
    PerformanceEntity toEntity(String performanceName);
    PerformanceEntity toEntity(Performance performance);
}
