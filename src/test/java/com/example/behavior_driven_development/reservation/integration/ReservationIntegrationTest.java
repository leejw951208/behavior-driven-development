package com.example.behavior_driven_development.reservation.integration;

import com.example.behavior_driven_development.reservation.dto.ReservationSaveRequestDto;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.base.BaseIntegrationTest;
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
        ReservationSaveRequestDto requestDto = new ReservationSaveRequestDto(customerName, RESERVATION_DATE);

        // when
        ResultActions actions = mockMvc.perform(post("/api/performances/{performanceId}/reservations", performanceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print());

        // then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.performanceId").value(performanceId))
                .andExpect(jsonPath("$.performanceName").value(PERFORMANCE_NAME))
                .andExpect(jsonPath("$.reservationDate").value(RESERVATION_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
    }

    @Test
    @DisplayName("통합 테스트: 공연 예약 실패")
    public void reservePerformanceFailed() throws Exception {
        // given
        long performanceId = savedPerformanceEntity.getId();
        String customerName = "고길동";
        ReservationSaveRequestDto requestDto = new ReservationSaveRequestDto(customerName, RESERVATION_DATE);

        // when
        ResultActions actions = mockMvc.perform(post("/api/performances/{performanceId}/reservations", performanceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print());

        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("이미 예약이 마감되었습니다."));
    }
}
