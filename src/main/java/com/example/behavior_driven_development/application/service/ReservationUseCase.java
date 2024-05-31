package com.example.behavior_driven_development.application.service;

import com.example.behavior_driven_development.adapter.in.web.dto.ReservationRequestDto;
import com.example.behavior_driven_development.application.port.in.ReservationInPort;
import com.example.behavior_driven_development.application.port.out.*;
import com.example.behavior_driven_development.domain.*;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReservationUseCase implements ReservationInPort {
    private final PerformanceFindOutPort performanceFindPort;
    private final InventorySaveOutPort inventorySavePort;
    private final ReservationSaveOutPort reservationSavePort;
    private final PerformanceMapper performanceMapper;

    @Override
    @Transactional
    public Reserved reserve(ReservationRequestDto requestDto) {
        long performanceId = requestDto.performanceId();
        String customerName = requestDto.customerName();
        LocalDate reservationDate = requestDto.reservationDate();

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
