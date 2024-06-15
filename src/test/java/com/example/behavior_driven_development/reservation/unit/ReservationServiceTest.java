package com.example.behavior_driven_development.reservation.unit;

import com.example.behavior_driven_development.reservation.domain.Inventory;
import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.domain.Reservation;
import com.example.behavior_driven_development.reservation.domain.ReservationSave;
import com.example.behavior_driven_development.reservation.dto.ReservationResponseDto;
import com.example.behavior_driven_development.reservation.dto.ReservationSaveRequestDto;
import com.example.behavior_driven_development.reservation.mapper.ReservationMapper;
import com.example.behavior_driven_development.reservation.repository.InventoryRepository;
import com.example.behavior_driven_development.reservation.repository.PerformanceRepository;
import com.example.behavior_driven_development.reservation.repository.ReservationRepository;
import com.example.behavior_driven_development.reservation.service.impl.ReservationServiceImpl;
import com.example.behavior_driven_development.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.testcontainers.shaded.org.bouncycastle.oer.its.ieee1609dot2.HashedData.reserved;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest extends BaseTest {
    @Mock
    private PerformanceRepository performanceRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    @DisplayName("서비스 단위 테스트: 공연 예약")
    public void saveReservation() {
        // given
        String customerName = "고길동";
        LocalDate reservationDate = LocalDate.now();
        ReservationSaveRequestDto requestDto = new ReservationSaveRequestDto(customerName, reservationDate);

        long inventoryId = 1L;
        int quantity = 1;
        Inventory inventory = new Inventory(inventoryId, quantity, reservationDate);

        long performanceId = 1L;
        String performanceName = "홍길동전";
        Performance performance = new Performance(performanceId, performanceName, LocalDate.now());

        ReservationSave reservationSave = new ReservationSave(customerName, reservationDate);
        Reservation reservation = new Reservation(performanceId, performanceName, reservationDate);
        ReservationResponseDto responseDto = new ReservationResponseDto(performanceId, performanceName, reservationDate);

        given(inventoryRepository.findInventory(performanceId, reservationDate, 0)).willReturn(inventory);
        given(performanceRepository.findPerformance(performanceId)).willReturn(performance);
        willDoNothing().given(inventoryRepository).save(performance, inventory);
        given(reservationMapper.toDomain(customerName, reservationDate)).willReturn(reservationSave);
        given(reservationRepository.save(performance, reservationSave)).willReturn(reservation);
        given(reservationMapper.toDto(reservation)).willReturn(responseDto);

        // when
        ReservationResponseDto result = reservationService.saveReservation(performanceId, requestDto);

        // then
        assertNotNull(result);
        assertEquals(responseDto, result);
        assertEquals(inventory.getQuantity(), quantity - 1);
    }

    @Test
    @DisplayName("서비스 단위 테스트: 공연 예약 마감")
    public void reservePerformanceFailed() {
        // given
        long performanceId = 1L;
        String customerName = "고길동";
        LocalDate reservationDate = LocalDate.now();
        int quantity = 0;

        ReservationSaveRequestDto requestDto = new ReservationSaveRequestDto(customerName, reservationDate);
        Inventory inventory = Inventory.builder().inventoryId(performanceId).quantity(quantity).reservationDate(reservationDate).build();

        given(inventoryRepository.findInventory(performanceId, reservationDate, quantity)).willThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 예약이 마감되었습니다."));

        // when
        Throwable throwable = catchThrowable(() -> reservationService.saveReservation(performanceId, requestDto));

        // then
        assertThat(throwable).isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("이미 예약이 마감되었습니다.");
    }
}
