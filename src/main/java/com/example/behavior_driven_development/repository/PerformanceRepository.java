package com.example.behavior_driven_development.repository;

import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceInventory;

import java.time.LocalDate;

public interface PerformanceRepository {
    Performance findPerformance(long performanceId);
    void save(String performanceName);
}
