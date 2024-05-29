package com.example.behavior_driven_development.application.port.out;

import com.example.behavior_driven_development.domain.PerformanceReservable;
import com.example.behavior_driven_development.domain.PerformanceReserve;
import com.example.behavior_driven_development.domain.PerformanceReserved;

public interface PerformanceSavePort {
    PerformanceReserved savePerformanceReserve(PerformanceReserve performanceReserve);
}
