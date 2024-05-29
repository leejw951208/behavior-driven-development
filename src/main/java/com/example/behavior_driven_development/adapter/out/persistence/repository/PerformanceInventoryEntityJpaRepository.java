package com.example.behavior_driven_development.adapter.out.persistence.repository;

import com.example.behavior_driven_development.adapter.out.persistence.PerformanceInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceInventoryEntityJpaRepository extends JpaRepository<PerformanceInventoryEntity, Long> {
}
