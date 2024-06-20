package com.example.behavior_driven_development.reservation.service;

import com.example.behavior_driven_development.common.dto.PagingDto;
import com.example.behavior_driven_development.reservation.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.reservation.dto.PerformanceResponseDto;

public interface PerformanceService {
    void savePerformance(PerformanceRequestDto requestDto);

    PagingDto<PerformanceResponseDto> findPerformances(int page, int perPage);
}
