package com.example.behavior_driven_development.service.impl;

import com.example.behavior_driven_development.dto.ReservationSaveRequestDto;
import com.example.behavior_driven_development.dto.ReservationResponseDto;
import com.example.behavior_driven_development.mapper.ReservationMapper;
import com.example.behavior_driven_development.repository.InventoryRepository;
import com.example.behavior_driven_development.repository.PerformanceRepository;
import com.example.behavior_driven_development.repository.ReservationRepository;
import com.example.behavior_driven_development.service.ReservationService;
import com.example.behavior_driven_development.domain.*;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
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
        Inventory findInventory = inventoryRepository.findInventory(performanceId, reservationDate);
        if (findInventory.getQuantity() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 예약이 마감되었습니다.");
        }
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
