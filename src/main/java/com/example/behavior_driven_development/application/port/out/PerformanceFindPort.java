package com.example.behavior_driven_development.application.port.out;

import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceReservable;

import java.time.LocalDate;

public interface PerformanceFindPort {
    Performance findReservable(long performanceId, LocalDate reservableDate);
}
