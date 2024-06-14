package com.example.behavior_driven_development.controller;

import com.example.behavior_driven_development.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerformanceController {
    private final PerformanceService performanceService;

    @PostMapping("/api/performances")
    public ResponseEntity<String> savePerformance(@RequestBody PerformanceRequestDto requestDto) {
        performanceService.savePerformance(requestDto);
        return new ResponseEntity<>("succeed", HttpStatus.CREATED);
    }
}
