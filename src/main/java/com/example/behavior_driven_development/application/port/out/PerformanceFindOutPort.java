package com.example.behavior_driven_development.application.port.out;

import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceInventory;

import java.time.LocalDate;

public interface PerformanceFindOutPort {
    PerformanceInventory findByIdAndReservationDate(long performanceId, LocalDate reservationDate);
    Performance findPerformance(long performanceId);
}
