package com.example.behavior_driven_development.reservation.unit;

import com.example.behavior_driven_development.adapter.in.web.dto.ReservationRequestDto;
import com.example.behavior_driven_development.adapter.in.web.dto.ReservationResponseDto;
import com.example.behavior_driven_development.application.port.out.InventoryFindOutPort;
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
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest extends BaseTest {
    @Mock
    private PerformanceFindOutPort performanceFindPort;

    @Mock
    private InventoryFindOutPort inventoryFindOutPort;

    @Mock
    private InventorySaveOutPort inventorySavePort;

    @Mock
    private ReservationSaveOutPort reservationSavePort;

    @Mock
    private PerformanceMapper performanceMapper;

    @InjectMocks
    private ReservationUseCase reservationService;

    private static final LocalDate date = LocalDate.now();

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
        Reservation reservation = Reservation.builder().performance(performance).customerName(customerName).reservationDate(date).build();
        Reserved reserved = Reserved.builder().performanceId(performanceId).performanceName("홍길동전").customerName(customerName).reservationDate(date).build();

        given(inventoryFindOutPort.findInventory(performanceId, reservationDate)).willReturn(inventory);
        given(performanceFindPort.findPerformance(performanceId)).willReturn(performance);
        willDoNothing().given(inventorySavePort).updateInventory(inventory, performance);
        given(performanceMapper.toDomain(performance, customerName, reservationDate)).willReturn(reservation);
        given(reservationSavePort.save(reservation)).willReturn(reserved);

        // when
        Reserved response = reservationService.reserve(requestDto);

        // then
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
        Inventory inventory = Inventory.builder().inventoryId(performanceId).quantity(0).reservationDate(date).build();

        given(inventoryFindOutPort.findInventory(performanceId, reservationDate)).willReturn(inventory);

        // when
        Throwable throwable = catchThrowable(() -> reservationService.reserve(requestDto));

        // then
        assertThat(throwable).isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("공연 예약이 마감되었습니다.");
    }
}
