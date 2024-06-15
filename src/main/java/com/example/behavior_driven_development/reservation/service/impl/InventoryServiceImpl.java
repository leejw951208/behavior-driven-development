package com.example.behavior_driven_development.reservation.service.impl;

import com.example.behavior_driven_development.reservation.domain.InventorySave;
import com.example.behavior_driven_development.reservation.dto.InventorySaveRequestDto;
import com.example.behavior_driven_development.reservation.mapper.InventoryMapper;
import com.example.behavior_driven_development.reservation.repository.InventoryRepository;
import com.example.behavior_driven_development.reservation.service.InventoryService;
import com.example.behavior_driven_development.reservation.repository.PerformanceRepository;
import com.example.behavior_driven_development.reservation.domain.Performance;
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
