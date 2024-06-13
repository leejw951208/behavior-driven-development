package com.example.behavior_driven_development.application.service;

import com.example.behavior_driven_development.adapter.in.web.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.application.port.in.PerformanceInPort;
import com.example.behavior_driven_development.application.port.out.PerformanceSaveOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceUseCase implements PerformanceInPort {
    private final PerformanceSaveOutPort performanceSaveOutPort;

    @Override
    public void register(String performanceName) {
        performanceSaveOutPort.save(performanceName);
    }
}
