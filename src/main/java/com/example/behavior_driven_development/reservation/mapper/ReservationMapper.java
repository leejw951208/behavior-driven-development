package com.example.behavior_driven_development.reservation.mapper;

import com.example.behavior_driven_development.reservation.domain.ReservationSave;
import com.example.behavior_driven_development.reservation.dto.ReservationResponseDto;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.reservation.domain.Reservation;

import java.time.LocalDate;

public interface ReservationMapper {
    ReservationEntity toEntity(PerformanceEntity performanceEntity, ReservationSave reservationSave);
    Reservation toDomain(ReservationEntity reservationEntity);
    ReservationSave toDomain(String customerName, LocalDate reservationDate);
    ReservationResponseDto toDto(Reservation reservation);
}
