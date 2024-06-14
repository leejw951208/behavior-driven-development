package com.example.behavior_driven_development.repository;

import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.persistence.reservation.ReservationEntityJpaRepository;
import com.example.behavior_driven_development.domain.ReservationSave;
import com.example.behavior_driven_development.domain.Reservation;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import com.example.behavior_driven_development.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {
    private final ReservationEntityJpaRepository reservationEntityJpaRepository;
    private final PerformanceMapper performanceMapper;
    private final ReservationMapper reservationMapper;

    @Override
    public Reservation save(Performance performance, ReservationSave reservationSave) {
        PerformanceEntity createdPerformanceEntity = performanceMapper.toEntity(performance);
        ReservationEntity createdReservationEntity = reservationMapper.toEntity(createdPerformanceEntity, reservationSave);
        ReservationEntity savedReservationEntity = reservationEntityJpaRepository.save(createdReservationEntity);
        return reservationMapper.toDomain(savedReservationEntity);
    }
}
