package com.example.behavior_driven_development.adapter.out.persistence.repository;

import com.example.behavior_driven_development.adapter.out.persistence.PerformanceReserveEntity;
import com.example.behavior_driven_development.domain.PerformanceReserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceReserveEntityJpaRepository extends JpaRepository<PerformanceReserveEntity, Long> {
}
