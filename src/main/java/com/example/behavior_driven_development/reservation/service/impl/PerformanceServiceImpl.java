package com.example.behavior_driven_development.reservation.service.impl;

import com.example.behavior_driven_development.reservation.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.reservation.repository.PerformanceRepository;
import com.example.behavior_driven_development.reservation.service.PerformanceService;
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
