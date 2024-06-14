package com.example.behavior_driven_development.mapper;

import com.example.behavior_driven_development.domain.ReservationSave;
import com.example.behavior_driven_development.dto.ReservationResponseDto;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.domain.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReservationMapperImpl implements ReservationMapper {
    @Override
    public ReservationEntity toEntity(PerformanceEntity performanceEntity, ReservationSave reservationSave) {
        return ReservationEntity.builder()
                .performanceEntity(performanceEntity)
                .customerName(reservationSave.getCustomerName())
                .reservationDate(reservationSave.getReservationDate())
                .build();
    }

    @Override
    public Reservation toDomain(ReservationEntity reservationEntity) {
        return new Reservation(
                reservationEntity.getPerformanceEntity().getId(),
                reservationEntity.getPerformanceEntity().getPerformanceName(),
                reservationEntity.getReservationDate()
        );
    }

    @Override
    public ReservationSave toDomain(String customerName, LocalDate reservationDate) {
        return new ReservationSave(customerName, reservationDate);
    }

    @Override
    public ReservationResponseDto toDto(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.getPerformanceId(),
                reservation.getPerformanceName(),
                reservation.getReservationDate()
        );
    }
}
