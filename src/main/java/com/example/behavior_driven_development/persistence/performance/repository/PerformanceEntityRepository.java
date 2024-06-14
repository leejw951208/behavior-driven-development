package com.example.behavior_driven_development.persistence.performance.repository;

import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface PerformanceEntityRepository {
    Optional<PerformanceEntity> findPerformance(long performanceId);
}
