package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.adapter.out.persistence.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.PerformanceReserveEntity;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceReservable;
import com.example.behavior_driven_development.domain.PerformanceReserve;
import com.example.behavior_driven_development.domain.PerformanceReserved;

import java.time.LocalDate;

public interface PerformanceMapper {
    Performance toDomain(PerformanceEntity performanceEntity);
    PerformanceReserve toDomain(Performance performance, String customerName, LocalDate reserveDate);
    PerformanceReserved toDomain(PerformanceReserveEntity performanceReserveEntity);
    PerformanceReserveEntity toEntity(PerformanceEntity performanceEntity, String customerName, LocalDate reserveDate);
    PerformanceEntity toEntity(Performance performance);
}
