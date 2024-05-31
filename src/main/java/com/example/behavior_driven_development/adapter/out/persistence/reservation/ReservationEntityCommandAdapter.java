package com.example.behavior_driven_development.adapter.out.persistence.reservation;

import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.application.port.out.ReservationSaveOutPort;
import com.example.behavior_driven_development.domain.Reservation;
import com.example.behavior_driven_development.domain.Reserved;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import com.example.behavior_driven_development.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationEntityCommandAdapter implements ReservationSaveOutPort {
    private final ReservationEntityJpaRepository reservationEntityJpaRepository;
    private final PerformanceMapper performanceMapper;
    private final ReservationMapper reservationMapper;

    @Override
    public Reserved save(Reservation reservation) {
        PerformanceEntity performanceEntity = performanceMapper.toEntity(reservation.getPerformance());
        ReservationEntity reservationEntity = reservationMapper.toEntity(performanceEntity, reservation.getCustomerName(), reservation.getReservationDate());
        ReservationEntity savedReservationEntity = reservationEntityJpaRepository.save(reservationEntity);
        return reservationMapper.toDomain(savedReservationEntity);
    }
}
