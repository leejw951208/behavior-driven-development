package com.example.behavior_driven_development.application.port.out;

import com.example.behavior_driven_development.domain.Inventory;

public interface InventoryFindOutPort {
    Inventory findByPerformanceIdAndReservationDate(long performanceId);
}
