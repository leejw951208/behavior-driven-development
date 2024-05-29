package com.example.behavior_driven_development.adapter.out.persistence.repository;

import com.example.behavior_driven_development.adapter.out.persistence.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceEntityJpaRepository extends JpaRepository<PerformanceEntity, Long> {
}
