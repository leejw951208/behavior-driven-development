package com.example.behavior_driven_development.adapter.in.web;

import com.example.behavior_driven_development.adapter.in.web.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.application.port.in.PerformanceInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerformanceController {
    private final PerformanceInPort performanceInPort;

    @PostMapping("/api/v1/performance")
    public ResponseEntity<String> savePerformance(@RequestBody PerformanceRequestDto requestDto) {
        performanceInPort.register(requestDto.performanceName());
        return new ResponseEntity<>("succeed", HttpStatus.CREATED);
    }
}
