package com.example.behavior_driven_development.application.port.out;

import com.example.behavior_driven_development.domain.Inventory;

public interface InventorySavePort {
    void updateInventory(Inventory inventory);
}
