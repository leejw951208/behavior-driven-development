package com.example.behavior_driven_development.reservation.service;

import com.example.behavior_driven_development.reservation.dto.InventorySaveRequestDto;

import java.util.List;

public interface InventoryService {
    void saveInventories(long performanceId, List<InventorySaveRequestDto> requestDtos);
}
