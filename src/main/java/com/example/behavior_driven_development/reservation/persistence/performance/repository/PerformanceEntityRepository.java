package com.example.behavior_driven_development.reservation.persistence.performance.repository;

import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.dto.PerformanceResponseDto;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PerformanceEntityRepository {
    Optional<PerformanceEntity> findPerformance(long performanceId);
    Page<PerformanceResponseDto> findPerformances(Pageable pageable);
}
