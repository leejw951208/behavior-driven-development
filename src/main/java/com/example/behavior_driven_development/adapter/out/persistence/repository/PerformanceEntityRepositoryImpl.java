package com.example.behavior_driven_development.adapter.out.persistence.repository;

import com.example.behavior_driven_development.adapter.out.persistence.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.QPerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.QPerformanceInventoryEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.behavior_driven_development.adapter.out.persistence.QPerformanceEntity.performanceEntity;
import static com.example.behavior_driven_development.adapter.out.persistence.QPerformanceInventoryEntity.performanceInventoryEntity;

@Repository
@RequiredArgsConstructor
public class PerformanceEntityRepositoryImpl implements PerformanceEntityRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByIdAndReserveDate(long performanceId, LocalDate reserveDate) {
        Integer fetchOne = queryFactory
                .selectOne()
                .innerJoin(performanceEntity).on(performanceInventoryEntity.performanceEntity.eq(performanceEntity))
                .where(
                        performanceEntity.id.eq(performanceId),
                        performanceInventoryEntity.reservableDate.eq(reserveDate),
                        performanceInventoryEntity.quantity.gt(0)
                )
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .limit(1L)
                .fetchOne();
        return fetchOne != null;
    }

    @Override
    public Optional<PerformanceEntity> findById(long performanceId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(performanceEntity)
                        .where(performanceEntity.id.eq(performanceId))
                        .fetchOne()
        );
    }
}
