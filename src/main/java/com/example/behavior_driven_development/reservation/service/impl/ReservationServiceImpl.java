package com.example.behavior_driven_development.reservation.service.impl;

import com.example.behavior_driven_development.reservation.domain.Inventory;
import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.domain.Reservation;
import com.example.behavior_driven_development.reservation.domain.ReservationSave;
import com.example.behavior_driven_development.reservation.dto.ReservationSaveRequestDto;
import com.example.behavior_driven_development.reservation.dto.ReservationResponseDto;
import com.example.behavior_driven_development.reservation.mapper.ReservationMapper;
import com.example.behavior_driven_development.reservation.repository.InventoryRepository;
import com.example.behavior_driven_development.reservation.repository.PerformanceRepository;
import com.example.behavior_driven_development.reservation.repository.ReservationRepository;
import com.example.behavior_driven_development.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final PerformanceRepository performanceRepository;
    private final InventoryRepository inventoryRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    @Transactional
    public ReservationResponseDto saveReservation(long performanceId, ReservationSaveRequestDto requestDto) {
        String customerName = requestDto.customerName();
        LocalDate reservationDate = requestDto.reservationDate();

        // 남아있는 좌석 체크
        Inventory findInventory = inventoryRepository.findInventory(performanceId, reservationDate, 0);
        findInventory.decreaseQuantity();

        // 좌석 재고 업데이트
        Performance findPerformance = performanceRepository.findPerformance(performanceId);
        inventoryRepository.save(findPerformance, findInventory);

        // 공연 예약 정보 저장
        ReservationSave createdReservationSave = reservationMapper.toDomain(customerName, reservationDate);
        Reservation savedReservation = reservationRepository.save(findPerformance, createdReservationSave);

        return reservationMapper.toDto(savedReservation);
    }
}
