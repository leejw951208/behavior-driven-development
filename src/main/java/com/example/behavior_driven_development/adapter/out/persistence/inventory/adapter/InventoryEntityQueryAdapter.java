package com.example.behavior_driven_development.adapter.out.persistence.inventory.adapter;

import com.example.behavior_driven_development.application.port.out.InventoryFindOutPort;
import com.example.behavior_driven_development.domain.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InventoryEntityQueryAdapter implements InventoryFindOutPort {
    @Override
    public Inventory findByPerformanceIdAndReservationDate(long performanceId) {
        return null;
    }
}
