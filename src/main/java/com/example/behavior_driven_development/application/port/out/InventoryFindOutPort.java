package com.example.behavior_driven_development.application.port.out;

import com.example.behavior_driven_development.domain.Inventory;

import java.time.LocalDate;

public interface InventoryFindOutPort {
    Inventory findInventory(long performanceId, LocalDate reservationDate);
}
