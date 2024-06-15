package com.example.behavior_driven_development.reservation.persistence.performance.repository;

import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.persistence.performance.QPerformanceEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.behavior_driven_development.reservation.persistence.performance.QPerformanceEntity.performanceEntity;

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
