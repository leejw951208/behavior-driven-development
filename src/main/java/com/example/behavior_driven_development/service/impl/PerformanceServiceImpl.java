package com.example.behavior_driven_development.service.impl;

import com.example.behavior_driven_development.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.repository.PerformanceRepository;
import com.example.behavior_driven_development.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;

    @Override
    public void savePerformance(PerformanceRequestDto requestDto) {
        String performanceName = requestDto.performanceName();
        performanceRepository.save(performanceName);
    }
}
