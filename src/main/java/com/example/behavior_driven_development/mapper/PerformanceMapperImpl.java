package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.Reservation;
import com.example.behavior_driven_development.domain.Reserved;
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
    public Reservation toDomain(Performance performance, String customerName, LocalDate reserveDate) {
        return Reservation.builder()
                .performance(performance)
                .customerName(customerName)
                .reservationDate(reserveDate)
                .build();
    }

    @Override
    public Reserved toDomain(ReservationEntity performanceReserveEntity) {
        return Reserved.builder()
                .performanceId(performanceReserveEntity.getPerformanceEntity().getId())
                .performanceName(performanceReserveEntity.getPerformanceEntity().getPerformanceName())
                .customerName(performanceReserveEntity.getCustomerName())
                .reservationDate(performanceReserveEntity.getReservationDate())
                .build();
    }

    @Override
    public ReservationEntity toEntity(PerformanceEntity performanceEntity, String customerName, LocalDate reserveDate) {
        return ReservationEntity.builder()
                .performanceEntity(performanceEntity)
                .customerName(customerName)
                .reservationDate(reserveDate)
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

    @Override
    public InventoryEntity toEntity(Inventory inventory) {
        return InventoryEntity.builder()
                .id(inventory.getInventoryId())
                .quantity(inventory.getQuantity())
                .reservationDate(inventory.getReservationDate())
                .build();
    }
}
