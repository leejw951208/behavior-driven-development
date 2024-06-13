package com.example.behavior_driven_development.application.port.out;

import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Performance;

public interface InventorySaveOutPort {
    void updateInventory(Inventory inventory, Performance performance);
}
