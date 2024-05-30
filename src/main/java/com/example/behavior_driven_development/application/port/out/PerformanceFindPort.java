package com.example.behavior_driven_development.application.port.out;

import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.PerformanceInventory;

import java.time.LocalDate;

public interface PerformanceFindPort {
    PerformanceInventory findByIdAndReservationDate(long performanceId, LocalDate reservationDate);
}
