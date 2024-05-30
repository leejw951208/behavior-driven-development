package com.example.behavior_driven_development.unit;

import com.example.behavior_driven_development.application.port.out.InventorySavePort;
import com.example.behavior_driven_development.application.port.out.PerformanceFindPort;
import com.example.behavior_driven_development.application.port.out.ReservationSavePort;
import com.example.behavior_driven_development.application.service.ReservationService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest extends BaseTest {
    @Mock
    private PerformanceFindPort performanceFindPort;

    @Mock
    private InventorySavePort inventorySavePort;

    @Mock
    private ReservationSavePort reservationSavePort;

    @Mock
    private PerformanceMapper performanceMapper;

    @InjectMocks
    private ReservationService reservationService;

    private static final LocalDate date = LocalDate.of(2024, 5, 30);

    @Test
    @DisplayName("서비스 단위 테스트: 공연 예약")
    public void reservePerformance() {
        // given
        long performanceId = 1L;
        String customerName = "고길동";
        LocalDate reservationDate = date;

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
        Reserved response = reservationService.reserve(performanceId, customerName, reservationDate);

        // then
        then(performanceFindPort).should(times(1)).findByIdAndReservationDate(performanceId, reservationDate);
        then(inventorySavePort).should(times(1)).updateInventory(inventory);
        then(performanceMapper).should(times(1)).toDomain(performance, customerName, reservationDate);
        then(reservationSavePort).should(times(1)).save(reservation);

        assertNotNull(response);
        assertEquals(response, reserved);
        assertEquals(inventory.getQuantity(), 0);
    }
}
