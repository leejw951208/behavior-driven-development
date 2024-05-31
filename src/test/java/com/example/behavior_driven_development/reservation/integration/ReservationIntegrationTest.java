package com.example.behavior_driven_development.reservation.integration;

import com.example.behavior_driven_development.adapter.in.web.dto.ReservationRequestDto;
import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.base.BaseIntegrationTest;
import com.example.behavior_driven_development.exception.ResponseStatusExceptionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("통합 테스트: 공연 예약")
    public void reservePerformance() throws Exception {
        // given
        long performanceId = 1L;
        String customerName = "고길동";
        String performanceName = "홍길동전";
        int quantity = 0;
        LocalDate reservationDate = LocalDate.now();

        PerformanceEntity savedPerformanceEntity = reservationSetup.savePerformance(performanceName);
        reservationSetup.saveInventory(savedPerformanceEntity, reservationDate, quantity);

        ReservationRequestDto requestDto = new ReservationRequestDto(performanceId, customerName, reservationDate);
        String requestContent = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andDo(print());

        // then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.performanceId").value(1L))
                .andExpect(jsonPath("$.performanceName").value("홍길동전"))
                .andExpect(jsonPath("$.customerName").value("고길동"))
                .andExpect(jsonPath("$.reservationDate").value(reservationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
    }

    @Test
    @DisplayName("통합 테스트: 공연 예약 실패")
    public void reservePerformanceFailed() throws Exception {
        // given
        long performanceId = 1L;
        String customerName = "고길동";
        String performanceName = "홍길동전";
        int quantity = 0;
        LocalDate reservationDate = LocalDate.now();

        PerformanceEntity savedPerformanceEntity = reservationSetup.savePerformance(performanceName);
        reservationSetup.saveInventory(savedPerformanceEntity, reservationDate, quantity);

        ReservationRequestDto requestDto = new ReservationRequestDto(performanceId, customerName, reservationDate);
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
