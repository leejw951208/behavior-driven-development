package com.example.behavior_driven_development.reservation.repository;

import com.example.behavior_driven_development.reservation.domain.Performance;

public interface PerformanceRepository {
    Performance findPerformance(long performanceId);
    void save(String performanceName);
}
