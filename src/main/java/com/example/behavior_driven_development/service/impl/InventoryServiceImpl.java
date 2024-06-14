package com.example.behavior_driven_development.service.impl;

import com.example.behavior_driven_development.domain.InventorySave;
import com.example.behavior_driven_development.dto.InventorySaveRequestDto;
import com.example.behavior_driven_development.mapper.InventoryMapper;
import com.example.behavior_driven_development.repository.InventoryRepository;
import com.example.behavior_driven_development.service.InventoryService;
import com.example.behavior_driven_development.repository.PerformanceRepository;
import com.example.behavior_driven_development.domain.Performance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final PerformanceRepository performanceRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public void saveInventories(long performanceId, List<InventorySaveRequestDto> requestDtos) {
        Performance findPerformance = performanceRepository.findPerformance(performanceId);
        List<InventorySave> createdInventorySaves = inventoryMapper.toDomains(requestDtos);
        inventoryRepository.saveAll(findPerformance, createdInventorySaves);
    }
}
