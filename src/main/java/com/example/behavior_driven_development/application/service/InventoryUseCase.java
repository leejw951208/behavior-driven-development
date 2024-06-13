package com.example.behavior_driven_development.application.service;

import com.example.behavior_driven_development.application.port.in.InventoryInPort;
import com.example.behavior_driven_development.application.port.out.InventorySaveOutPort;
import com.example.behavior_driven_development.application.port.out.PerformanceFindOutPort;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryUseCase implements InventoryInPort {
    private final InventorySaveOutPort inventorySaveOutPort;
    private final PerformanceFindOutPort performanceFindOutPort;

    @Override
    public void register(long performanceId, int quantity) {
        Performance performance = performanceFindOutPort.findPerformance(performanceId);
        inventorySaveOutPort.save(quantity, performance);
    }
}
