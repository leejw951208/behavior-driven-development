package com.example.behavior_driven_development.reservation.repository;

import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.dto.PerformanceResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PerformanceRepository {
    Page<PerformanceResponseDto> findPerformances(Pageable pageable);
    Performance findPerformance(long performanceId);
    void save(String performanceName);
}
