package com.example.behavior_driven_development.controller;

import com.example.behavior_driven_development.domain.InventorySave;
import com.example.behavior_driven_development.dto.InventorySaveRequestDto;
import com.example.behavior_driven_development.mapper.InventoryMapper;
import com.example.behavior_driven_development.mapper.InventoryMapperImpl;
import com.example.behavior_driven_development.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/api/performances/{performanceId}/inventories")
    public ResponseEntity<String> saveInventories(
            @PathVariable long performanceId,
            @RequestBody List<InventorySaveRequestDto> requestDtos
    ) {
        inventoryService.saveInventories(performanceId, requestDtos);
        return new ResponseEntity<>("succeed", HttpStatus.CREATED);
    }
}
