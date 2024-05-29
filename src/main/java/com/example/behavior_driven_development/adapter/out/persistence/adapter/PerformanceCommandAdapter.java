package com.example.behavior_driven_development.adapter.out.persistence.adapter;

import com.example.behavior_driven_development.adapter.out.persistence.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.PerformanceReserveEntity;
import com.example.behavior_driven_development.adapter.out.persistence.repository.PerformanceEntityJpaRepository;
import com.example.behavior_driven_development.adapter.out.persistence.repository.PerformanceReserveEntityJpaRepository;
import com.example.behavior_driven_development.application.port.out.PerformanceSavePort;
import com.example.behavior_driven_development.domain.PerformanceReservable;
import com.example.behavior_driven_development.domain.PerformanceReserve;
import com.example.behavior_driven_development.domain.PerformanceReserved;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class PerformanceCommandAdapter implements PerformanceSavePort {
    private final PerformanceReserveEntityJpaRepository performanceReserveEntityJpaRepository;
    private final PerformanceMapper performanceMapper;

    @Override
    public PerformanceReserved savePerformanceReserve(PerformanceReserve performanceReserve) {
        PerformanceEntity performanceEntity = performanceMapper.toEntity(performanceReserve.getPerformance());
        String customerName = performanceReserve.getCustomerName();
        LocalDate reserveDate = performanceReserve.getReserveDate();
        PerformanceReserveEntity performanceReserveEntity = performanceMapper.toEntity(performanceEntity, customerName, reserveDate);

        PerformanceReserveEntity savedPerformanceReserveEntity = performanceReserveEntityJpaRepository.save(performanceReserveEntity);
        return performanceMapper.toDomain(savedPerformanceReserveEntity);
    }
}
