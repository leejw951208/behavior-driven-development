package com.example.behavior_driven_development.service;

import com.example.behavior_driven_development.domain.InventorySave;
import com.example.behavior_driven_development.dto.InventorySaveRequestDto;

import java.util.List;

public interface InventoryService {
    void saveInventories(long performanceId, List<InventorySaveRequestDto> requestDtos);
}
