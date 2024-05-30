package com.example.behavior_driven_development.adapter.out.persistence.performance.repository;

import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceEntityJpaRepository extends JpaRepository<PerformanceEntity, Long> {
}
