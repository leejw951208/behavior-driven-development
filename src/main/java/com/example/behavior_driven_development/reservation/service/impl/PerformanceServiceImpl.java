package com.example.behavior_driven_development.reservation.service.impl;

import com.example.behavior_driven_development.common.dto.PagingDto;
import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.reservation.dto.PerformanceResponseDto;
import com.example.behavior_driven_development.reservation.repository.PerformanceRepository;
import com.example.behavior_driven_development.reservation.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;

    @Override
    public void savePerformance(PerformanceRequestDto requestDto) {
        String performanceName = requestDto.performanceName();
        performanceRepository.save(performanceName);
    }

    @Override
    public PagingDto<PerformanceResponseDto> findPerformances(int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<PerformanceResponseDto> findPerformances = performanceRepository.findPerformances(pageable);
        return new PagingDto<>(findPerformances);
    }
}
