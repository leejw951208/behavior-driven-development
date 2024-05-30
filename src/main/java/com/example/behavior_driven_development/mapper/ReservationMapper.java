package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.domain.Reservation;
import com.example.behavior_driven_development.domain.Reserved;

import java.time.LocalDate;

public interface ReservationMapper {
    ReservationEntity toEntity(PerformanceEntity performanceEntity, String customerName, LocalDate reservationDate);
    Reserved toDomain(ReservationEntity reservationEntity);
}
