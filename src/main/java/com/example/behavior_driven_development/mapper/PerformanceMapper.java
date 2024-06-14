package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.ReservationSave;
import com.example.behavior_driven_development.domain.Reservation;

import java.time.LocalDate;

public interface PerformanceMapper {
    Performance toDomain(PerformanceEntity performanceEntity);
    PerformanceEntity toEntity(String performanceName);
    PerformanceEntity toEntity(Performance performance);
}
