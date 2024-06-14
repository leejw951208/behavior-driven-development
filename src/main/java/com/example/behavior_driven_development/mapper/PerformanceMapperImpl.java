package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.ReservationSave;
import com.example.behavior_driven_development.domain.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PerformanceMapperImpl implements PerformanceMapper {
    @Override
    public Performance toDomain(PerformanceEntity performanceEntity) {
        return Performance.builder()
                .performanceId(performanceEntity.getId())
                .performanceName(performanceEntity.getPerformanceName())
                .createdDate(performanceEntity.getCreatedDate())
                .build();
    }

    @Override
    public PerformanceEntity toEntity(String performanceName) {
        return PerformanceEntity.builder()
                .performanceName(performanceName)
                .build();
    }

    @Override
    public PerformanceEntity toEntity(Performance performance) {
        return PerformanceEntity.builder()
                .id(performance.getPerformanceId())
                .performanceName(performance.getPerformanceName())
                .createdDate(performance.getCreatedDate())
                .build();
    }
}
