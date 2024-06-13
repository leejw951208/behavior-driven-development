package com.example.behavior_driven_development.adapter.out.persistence.inventory.repository;

import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.behavior_driven_development.adapter.out.persistence.inventory.QInventoryEntity.inventoryEntity;
import static com.example.behavior_driven_development.adapter.out.persistence.performance.QPerformanceEntity.performanceEntity;

@Repository
@RequiredArgsConstructor
public class InventoryEntityRepositoryImpl implements InventoryEntityRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<InventoryEntity> findByPerformanceIdAndReservationDate(long performanceId, LocalDate reservationDate) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(inventoryEntity)
                        .innerJoin(performanceEntity)
                        .on(inventoryEntity.performanceEntity.eq(performanceEntity))
                        .where(
                                performanceEntity.id.eq(performanceId),
                                inventoryEntity.reservationDate.eq(reservationDate)
                        )
                        .fetchOne()
        );
    }
}
