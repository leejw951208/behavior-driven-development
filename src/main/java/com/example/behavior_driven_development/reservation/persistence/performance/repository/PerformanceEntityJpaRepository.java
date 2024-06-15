package com.example.behavior_driven_development.reservation.persistence.performance.repository;

import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceEntityJpaRepository extends JpaRepository<PerformanceEntity, Long> {
}
