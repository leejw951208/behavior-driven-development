package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.domain.Reserved;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReservationMapperImpl implements ReservationMapper {
    @Override
    public ReservationEntity toEntity(PerformanceEntity performanceEntity, String customerName, LocalDate reservationDate) {
        return ReservationEntity.builder()
                .performanceEntity(performanceEntity)
                .customerName(customerName)
                .reservationDate(reservationDate)
                .build();
    }

    @Override
    public Reserved toDomain(ReservationEntity reservationEntity) {
        return Reserved.builder()
                .performanceId(reservationEntity.getPerformanceEntity().getId())
                .performanceName(reservationEntity.getPerformanceEntity().getPerformanceName())
                .customerName(reservationEntity.getCustomerName())
                .reservationDate(reservationEntity.getReservationDate())
                .build();
    }
}
