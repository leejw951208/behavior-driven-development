package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.adapter.out.persistence.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.PerformanceReserveEntity;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceReservable;
import com.example.behavior_driven_development.domain.PerformanceReserve;
import com.example.behavior_driven_development.domain.PerformanceReserved;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PerformanceMapperImpl implements PerformanceMapper {
    @Override
    public Performance toDomain(PerformanceEntity performanceEntity) {
        return Performance.builder()
                .id(performanceEntity.getId())
                .performanceName(performanceEntity.getPerformanceName())
                .createdDate(performanceEntity.getCreatedDate())
                .build();
    }

    @Override
    public PerformanceReserve toDomain(Performance performance, String customerName, LocalDate reserveDate) {
        return PerformanceReserve.builder()
                .performance(performance)
                .customerName(customerName)
                .reserveDate(reserveDate)
                .build();
    }

    @Override
    public PerformanceReserved toDomain(PerformanceReserveEntity performanceReserveEntity) {
        return PerformanceReserved.builder()
                .performanceId(performanceReserveEntity.getPerformanceEntity().getId())
                .performanceName(performanceReserveEntity.getPerformanceEntity().getPerformanceName())
                .customerName(performanceReserveEntity.getCustomerName())
                .reservedDate(performanceReserveEntity.getReservedDate())
                .build();
    }

    @Override
    public PerformanceReserveEntity toEntity(PerformanceEntity performanceEntity, String customerName, LocalDate reserveDate) {
        return PerformanceReserveEntity.builder()
                .performanceEntity(performanceEntity)
                .customerName(customerName)
                .reservedDate(reserveDate)
                .build();
    }

    @Override
    public PerformanceEntity toEntity(Performance performance) {
        return PerformanceEntity.builder()
                .id(performance.getId())
                .performanceName(performance.getPerformanceName())
                .createdDate(performance.getCreatedDate())
                .build();
    }
}
