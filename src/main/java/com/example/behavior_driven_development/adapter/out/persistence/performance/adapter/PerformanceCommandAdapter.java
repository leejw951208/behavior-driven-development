package com.example.behavior_driven_development.adapter.out.persistence.performance.adapter;

import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntityJpaRepository;
import com.example.behavior_driven_development.application.port.out.PerformanceSavePort;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Reservation;
import com.example.behavior_driven_development.domain.Reserved;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class PerformanceCommandAdapter implements PerformanceSavePort {
    private final ReservationEntityJpaRepository reservationEntityJpaRepository;
    private final PerformanceMapper performanceMapper;

    @Override
    public Reserved saveReservation(Reservation reservation) {
        PerformanceEntity performanceEntity = performanceMapper.toEntity(reservation.getPerformance());
        String customerName = reservation.getCustomerName();
        LocalDate reserveDate = reservation.getReservationDate();
        ReservationEntity performanceReserveEntity = performanceMapper.toEntity(performanceEntity, customerName, reserveDate);

        ReservationEntity savedPerformanceReserveEntity = reservationEntityJpaRepository.save(performanceReserveEntity);
        return performanceMapper.toDomain(savedPerformanceReserveEntity);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        InventoryEntity inventoryEntity = performanceMapper.toEntity(inventory);
        inventoryEntity.decreaseQuantity();
    }
}
