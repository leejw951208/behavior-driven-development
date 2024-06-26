package com.example.behavior_driven_development.reservation.persistence.inventory.repository;

import com.example.behavior_driven_development.reservation.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.reservation.persistence.inventory.QInventoryEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.behavior_driven_development.reservation.persistence.inventory.QInventoryEntity.inventoryEntity;
import static com.example.behavior_driven_development.reservation.persistence.performance.QPerformanceEntity.performanceEntity;

@Repository
@RequiredArgsConstructor
public class InventoryEntityRepositoryImpl implements InventoryEntityRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<InventoryEntity> findInventory(long performanceId, LocalDate reservationDate, int quantity) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(inventoryEntity)
                        .innerJoin(performanceEntity)
                        .on(inventoryEntity.performanceEntity.eq(performanceEntity))
                        .where(
                                performanceEntity.id.eq(performanceId),
                                inventoryEntity.reservationDate.eq(reservationDate),
                                inventoryEntity.quantity.ne(quantity)
                        )
                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                        .fetchOne()
        );
    }
}
