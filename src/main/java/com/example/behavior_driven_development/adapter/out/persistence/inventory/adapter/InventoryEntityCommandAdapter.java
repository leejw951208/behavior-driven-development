package com.example.behavior_driven_development.adapter.out.persistence.inventory.adapter;

import com.example.behavior_driven_development.application.port.out.InventorySaveOutPort;
import com.example.behavior_driven_development.domain.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InventoryEntityCommandAdapter implements InventorySaveOutPort {
    @Override
    public void updateInventory(Inventory inventory) {

    }
}
