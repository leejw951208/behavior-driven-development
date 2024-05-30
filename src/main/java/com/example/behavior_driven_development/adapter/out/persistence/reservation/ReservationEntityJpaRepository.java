package com.example.behavior_driven_development.adapter.out.persistence.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationEntityJpaRepository extends JpaRepository<ReservationEntity, Long> {
}
