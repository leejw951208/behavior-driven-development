package com.example.behavior_driven_development.reservation.persistence.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationEntityJpaRepository extends JpaRepository<ReservationEntity, Long> {
}
