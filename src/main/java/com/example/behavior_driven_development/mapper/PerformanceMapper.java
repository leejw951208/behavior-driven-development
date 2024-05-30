package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.Reservation;
import com.example.behavior_driven_development.domain.Reserved;

import java.time.LocalDate;

public interface PerformanceMapper {
    Performance toDomain(PerformanceEntity performanceEntity);
    Reservation toDomain(Performance performance, String customerName, LocalDate reserveDate);
    Reserved toDomain(ReservationEntity performanceReserveEntity);
    ReservationEntity toEntity(PerformanceEntity performanceEntity, String customerName, LocalDate reserveDate);
    PerformanceEntity toEntity(Performance performance);
    InventoryEntity toEntity(Inventory inventory);
}
