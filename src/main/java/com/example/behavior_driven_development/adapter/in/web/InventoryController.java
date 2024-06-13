package com.example.behavior_driven_development.adapter.in.web;

import com.example.behavior_driven_development.adapter.in.web.dto.InventoryRequestDto;
import com.example.behavior_driven_development.application.port.in.InventoryInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryInPort inventoryInPort;

    @PostMapping("/api/v1/inventory")
    public ResponseEntity<String> saveInventory(@RequestBody InventoryRequestDto requestDto) {
        inventoryInPort.register(requestDto.performanceId(), requestDto.quantity());
        return new ResponseEntity<>("succeed", HttpStatus.CREATED);
    }
}
