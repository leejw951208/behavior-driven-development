package com.example.behavior_driven_development.application.service;

import com.example.behavior_driven_development.application.port.in.ReservationUseCase;
import com.example.behavior_driven_development.application.port.out.*;
import com.example.behavior_driven_development.domain.*;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationUseCase {
    private final PerformanceFindPort performanceFindPort;
    private final InventorySavePort inventorySavePort;
    private final ReservationSavePort reservationSavePort;
    private final PerformanceMapper performanceMapper;

    @Override
    @Transactional
    public Reserved reserve(long performanceId, String customerName, LocalDate reservationDate) {
        PerformanceInventory performanceInventory = performanceFindPort.findByIdAndReservationDate(performanceId, reservationDate);
        if (performanceInventory.getInventory().getQuantity() == 0) {
            throw new RuntimeException("예약이 마감된 공연 입니다.");
        }

        Inventory inventory = performanceInventory.getInventory();
        inventory.decreaseQuantity();
        inventorySavePort.updateInventory(inventory);

        Reservation reservation = performanceMapper.toDomain(performanceInventory.getPerformance(), customerName, reservationDate);
        return reservationSavePort.save(reservation);
    }
}
