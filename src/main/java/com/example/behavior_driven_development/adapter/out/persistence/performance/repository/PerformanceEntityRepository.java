package com.example.behavior_driven_development.adapter.out.persistence.performance.repository;

import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceInventory;

import java.time.LocalDate;
import java.util.Optional;

public interface PerformanceEntityRepository {
    boolean existsByIdAndReserveDate(long performanceId, LocalDate reserveDate);
    Optional<PerformanceEntity> findById(long performanceId);
    Optional<Performance> findPerformanceById(long performanceId);
    Optional<PerformanceInventory> findByIdAndReservationDate(long performanceId, LocalDate reservationDate);
}
