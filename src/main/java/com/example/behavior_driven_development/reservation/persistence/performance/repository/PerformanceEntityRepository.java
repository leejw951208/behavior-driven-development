package com.example.behavior_driven_development.reservation.persistence.performance.repository;

import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;

import java.util.Optional;

public interface PerformanceEntityRepository {
    Optional<PerformanceEntity> findPerformance(long performanceId);
}
