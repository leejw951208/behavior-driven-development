package com.example.behavior_driven_development.reservation.unit;

import com.example.behavior_driven_development.adapter.in.web.dto.ReservationRequestDto;
import com.example.behavior_driven_development.adapter.in.web.dto.ReservationResponseDto;
import com.example.behavior_driven_development.application.port.out.InventorySaveOutPort;
import com.example.behavior_driven_development.application.port.out.PerformanceFindOutPort;
import com.example.behavior_driven_development.application.port.out.ReservationSaveOutPort;
import com.example.behavior_driven_development.application.service.ReservationUseCase;
import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.domain.*;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest extends BaseTest {
    @Mock
    private PerformanceFindOutPort performanceFindPort;

    @Mock
    private InventorySaveOutPort inventorySavePort;

    @Mock
    private ReservationSaveOutPort reservationSavePort;

    @Mock
    private PerformanceMapper performanceMapper;

    @InjectMocks
    private ReservationUseCase reservationService;

    private static final LocalDate date = LocalDate.of(2024, 5, 30);

    @Test
    @DisplayName("서비스 단위 테스트: 공연 예약")
    public void reservePerformance() {
        // given
        long performanceId = 1L;
        String customerName = "고길동";
        LocalDate reservationDate = date;

        ReservationRequestDto requestDto = new ReservationRequestDto(performanceId, customerName, reservationDate);
        Performance performance = Performance.builder().performanceId(performanceId).performanceName("홍길동전").createdDate(date).build();
        Inventory inventory = Inventory.builder().inventoryId(performanceId).quantity(1).reservationDate(date).build();
        PerformanceInventory performanceInventory = PerformanceInventory.builder().performance(performance).inventory(inventory).build();
        Reservation reservation = Reservation.builder().performance(performance).customerName(customerName).reservationDate(date).build();
        Reserved reserved = Reserved.builder().performanceId(performanceId).performanceName("홍길동전").customerName(customerName).reservationDate(date).build();

        given(performanceFindPort.findByIdAndReservationDate(performanceId, reservationDate)).willReturn(performanceInventory);
        willDoNothing().given(inventorySavePort).updateInventory(inventory);
        given(performanceMapper.toDomain(performance, customerName, reservationDate)).willReturn(reservation);
        given(reservationSavePort.save(reservation)).willReturn(reserved);

        // when
        Reserved response = reservationService.reserve(requestDto);

        // then
        then(performanceFindPort).should(times(1)).findByIdAndReservationDate(performanceId, reservationDate);
        then(inventorySavePort).should(times(1)).updateInventory(inventory);
        then(performanceMapper).should(times(1)).toDomain(performance, customerName, reservationDate);
        then(reservationSavePort).should(times(1)).save(reservation);

        assertNotNull(response);
        assertEquals(response, reserved);
        assertEquals(inventory.getQuantity(), 0);
    }

    @Test
    @DisplayName("서비스 단위 테스트: 공연 예약 마감")
    public void reservePerformanceFailed() {
        // given
        long performanceId = 1L;
        String customerName = "고길동";
        LocalDate reservationDate = date;

        ReservationRequestDto requestDto = new ReservationRequestDto(performanceId, customerName, reservationDate);
        Performance performance = Performance.builder().performanceId(performanceId).performanceName("홍길동전").createdDate(date).build();
        Inventory inventory = Inventory.builder().inventoryId(performanceId).quantity(0).reservationDate(date).build();
        PerformanceInventory performanceInventory = PerformanceInventory.builder().performance(performance).inventory(inventory).build();

        given(performanceFindPort.findByIdAndReservationDate(performanceId, reservationDate)).willReturn(performanceInventory);

        // when
        RuntimeException ex = assertThrows(RuntimeException.class
                , () -> reservationService.reserve(requestDto));

        // then
        assertEquals(ex.getMessage(), "공연 예약이 마감되었습니다.");
    }
}
