package com.example.behavior_driven_development.persistence.performance.repository;

import com.example.behavior_driven_development.persistence.inventory.QInventoryEntity;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceInventory;
import com.example.behavior_driven_development.persistence.performance.QPerformanceEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.behavior_driven_development.persistence.inventory.QInventoryEntity.inventoryEntity;
import static com.example.behavior_driven_development.persistence.performance.QPerformanceEntity.performanceEntity;

@Repository
@RequiredArgsConstructor
public class PerformanceEntityRepositoryImpl implements PerformanceEntityRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PerformanceEntity> findPerformance(long performanceId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(performanceEntity)
                        .where(performanceEntity.id.eq(performanceId))
                        .fetchOne()
        );
    }
}
