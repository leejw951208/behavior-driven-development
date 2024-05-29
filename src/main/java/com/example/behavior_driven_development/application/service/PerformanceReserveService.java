package com.example.behavior_driven_development.application.service;

import com.example.behavior_driven_development.application.port.in.PerformanceReserveUseCase;
import com.example.behavior_driven_development.application.port.out.PerformanceFindPort;
import com.example.behavior_driven_development.application.port.out.PerformanceSavePort;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceReservable;
import com.example.behavior_driven_development.domain.PerformanceReserve;
import com.example.behavior_driven_development.domain.PerformanceReserved;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PerformanceReserveService implements PerformanceReserveUseCase {
    private final PerformanceFindPort performanceFindPort;
    private final PerformanceSavePort performanceSavePort;
    private final PerformanceMapper performanceMapper;

    @Override
    public PerformanceReserved reservePerformance(PerformanceReservable performanceReservable) {
        long performanceId = performanceReservable.getPerformanceId();
        String customerName = performanceReservable.getCustomerName();
        LocalDate reservableDate = performanceReservable.getReservableDate();

        Performance findPerformance = performanceFindPort.findReservable(performanceId, reservableDate);
        PerformanceReserve performanceReserve = performanceMapper.toDomain(findPerformance, customerName, reservableDate);
        return performanceSavePort.savePerformanceReserve(performanceReserve);
    }
}
