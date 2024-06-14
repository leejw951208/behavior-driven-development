package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.domain.ReservationSave;
import com.example.behavior_driven_development.dto.ReservationResponseDto;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.domain.Reservation;

import java.time.LocalDate;

public interface ReservationMapper {
    ReservationEntity toEntity(PerformanceEntity performanceEntity, ReservationSave reservationSave);
    Reservation toDomain(ReservationEntity reservationEntity);
    ReservationSave toDomain(String customerName, LocalDate reservationDate);
    ReservationResponseDto toDto(Reservation reservation);
}
