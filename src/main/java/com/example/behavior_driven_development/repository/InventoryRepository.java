package com.example.behavior_driven_development.repository;

import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.InventorySave;
import com.example.behavior_driven_development.domain.Performance;

import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository {
    Inventory findInventory(long performanceId, LocalDate reservationDate);
    void saveAll(Performance performance, List<InventorySave> inventorySaves);
    void save(Performance performance, Inventory inventory);
}
