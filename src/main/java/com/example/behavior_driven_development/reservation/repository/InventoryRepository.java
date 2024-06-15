package com.example.behavior_driven_development.reservation.repository;

import com.example.behavior_driven_development.reservation.domain.Inventory;
import com.example.behavior_driven_development.reservation.domain.InventorySave;
import com.example.behavior_driven_development.reservation.domain.Performance;

import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository {
    Inventory findInventory(long performanceId, LocalDate reservationDate, int quantity);
    void saveAll(Performance performance, List<InventorySave> inventorySaves);
    void save(Performance performance, Inventory inventory);
}
