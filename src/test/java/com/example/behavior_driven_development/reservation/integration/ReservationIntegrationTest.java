package com.example.behavior_driven_development.reservation.integration;

import com.example.behavior_driven_development.adapter.in.web.dto.ReservationRequestDto;
import com.example.behavior_driven_development.adapter.out.persistence.inventory.InventoryEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.base.BaseIntegrationTest;
import com.example.behavior_driven_development.exception.ResponseStatusExceptionDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReservationIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private ReservationSetup reservationSetup;

    private static final LocalDate RESERVATION_DATE = LocalDate.now();
    private static final String PERFORMANCE_NAME = "홍길동전";
    private PerformanceEntity savedPerformanceEntity;

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        if (testInfo.getDisplayName().equals("통합 테스트: 공연 예약")) {
            savedPerformanceEntity = reservationSetup.savePerformance(PERFORMANCE_NAME);
            reservationSetup.saveInventory(savedPerformanceEntity, RESERVATION_DATE, 1);
        } else {
            savedPerformanceEntity = reservationSetup.savePerformance(PERFORMANCE_NAME);
            reservationSetup.saveInventory(savedPerformanceEntity, RESERVATION_DATE, 0);
        }
    }

    @Test
    @DisplayName("통합 테스트: 공연 예약")
    public void reservePerformance() throws Exception {
        // given
        long performanceId = savedPerformanceEntity.getId();
        String customerName = "고길동";

        ReservationRequestDto requestDto = new ReservationRequestDto(performanceId, customerName, RESERVATION_DATE);
        String requestContent = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andDo(print());

        // then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.performanceId").value(performanceId))
                .andExpect(jsonPath("$.performanceName").value(PERFORMANCE_NAME))
                .andExpect(jsonPath("$.customerName").value("고길동"))
                .andExpect(jsonPath("$.reservationDate").value(RESERVATION_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
    }

    @Test
    @DisplayName("통합 테스트: 공연 예약 실패")
    public void reservePerformanceFailed() throws Exception {
        // given
        long performanceId = savedPerformanceEntity.getId();
        String customerName = "고길동";

        /*PerformanceEntity savedPerformanceEntity = reservationSetup.savePerformance(PERFORMANCE_NAME);
        reservationSetup.saveInventory(savedPerformanceEntity, RESERVATION_DATE, quantity);*/

        ReservationRequestDto requestDto = new ReservationRequestDto(performanceId, customerName, RESERVATION_DATE);
        String requestContent = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andDo(print());

        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("공연 예약이 마감되었습니다."));
    }
}
