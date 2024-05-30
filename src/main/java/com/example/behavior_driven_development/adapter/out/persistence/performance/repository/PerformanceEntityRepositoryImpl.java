package com.example.behavior_driven_development.adapter.out.persistence.performance.repository;

import com.example.behavior_driven_development.adapter.out.persistence.inventory.QInventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.QPerformanceEntity;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceInventory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.behavior_driven_development.adapter.out.persistence.inventory.QInventoryEntity.inventoryEntity;
import static com.example.behavior_driven_development.adapter.out.persistence.performance.QPerformanceEntity.performanceEntity;

@Repository
@RequiredArgsConstructor
public class PerformanceEntityRepositoryImpl implements PerformanceEntityRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByIdAndReserveDate(long performanceId, LocalDate reservationDate) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(inventoryEntity)
                .innerJoin(performanceEntity).on(inventoryEntity.performanceEntity.eq(performanceEntity))
                .where(
                        performanceEntity.id.eq(performanceId),
                        inventoryEntity.reservationDate.eq(reservationDate),
                        inventoryEntity.quantity.gt(0)
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

    @Override
    public Optional<Performance> findPerformanceById(long performanceId) {
        return Optional.empty();
    }

    @Override
    public Optional<PerformanceInventory> findByIdAndReservationDate(long performanceId, LocalDate reservationDate) {
        return Optional.ofNullable(
                queryFactory
                        .select(Projections.fields(PerformanceInventory.class,
                                Projections.fields(Performance.class,
                                        performanceEntity.id.as("performanceId"),
                                        performanceEntity.performanceName,
                                        performanceEntity.createdDate
                                ).as("performance"),
                                Projections.fields(Inventory.class,
                                        inventoryEntity.id.as("inventoryId"),
                                        inventoryEntity.quantity,
                                        inventoryEntity.reservationDate
                                ).as("inventory")
                        ))
                        .from(performanceEntity)
                        .innerJoin(inventoryEntity).on(performanceEntity.eq(inventoryEntity.performanceEntity))
                        .where(
                                performanceEntity.id.eq(performanceId),
                                inventoryEntity.reservationDate.eq(reservationDate)
                        )
                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                        .fetchOne()
        );
    }
}
