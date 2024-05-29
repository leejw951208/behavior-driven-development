package com.example.behavior_driven_development.adapter.out.persistence.repository;

import com.example.behavior_driven_development.adapter.out.persistence.PerformanceEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface PerformanceEntityRepository {
    boolean existsByIdAndReserveDate(long performanceId, LocalDate reserveDate);
    Optional<PerformanceEntity> findById(long performanceId);
}
