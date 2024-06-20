package com.example.behavior_driven_development.reservation.controller;

import com.example.behavior_driven_development.common.dto.PagingDto;
import com.example.behavior_driven_development.reservation.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.reservation.dto.PerformanceResponseDto;
import com.example.behavior_driven_development.reservation.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PerformanceController {
    private final PerformanceService performanceService;

    @PostMapping("/api/performances")
    public ResponseEntity<String> savePerformance(@RequestBody PerformanceRequestDto requestDto) {
        performanceService.savePerformance(requestDto);
        return new ResponseEntity<>("succeed", HttpStatus.CREATED);
    }

    @GetMapping("/api/performances")
    public ResponseEntity<PagingDto<PerformanceResponseDto>> findPerformancesPaging(@RequestParam int page, @RequestParam int perPage) {
        return new ResponseEntity<>(performanceService.findPerformances(page, perPage), HttpStatus.OK);
    }
}
