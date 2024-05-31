package com.example.behavior_driven_development.reservation.unit;

import com.example.behavior_driven_development.adapter.in.web.ReservationController;
import com.example.behavior_driven_development.adapter.in.web.dto.ReservationRequestDto;
import com.example.behavior_driven_development.adapter.in.web.dto.ReservationResponseDto;
import com.example.behavior_driven_development.application.port.in.ReservationInPort;
import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.domain.Reserved;
import com.example.behavior_driven_development.mapper.ReservationMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReservationInPort reservationInPort;

    @MockBean
    private ReservationMapper reservationMapper;

    @Test
    @DisplayName("컨트롤러 단위 테스트: 공연 예약")
    public void reservePerformance() throws Exception {
        // given
        long performanceId = 1L;
        String customerName = "고길동";
        LocalDate reservationDate = LocalDate.now();

        ReservationRequestDto requestDto = new ReservationRequestDto(performanceId, customerName, reservationDate);
        String requestContent = objectMapper.writeValueAsString(requestDto);

        Reserved reserved = Reserved.builder().performanceId(performanceId).performanceName("홍길동전").customerName(customerName).reservationDate(reservationDate).build();
        ReservationResponseDto responseDto = new ReservationResponseDto(performanceId, "홍길동전", customerName, reservationDate);
        String responseContent = objectMapper.writeValueAsString(responseDto);

        given(reservationInPort.reserve(requestDto)).willReturn(reserved);
        given(reservationMapper.toDto(reserved)).willReturn(responseDto);

        // when
        ResultActions actions = mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andDo(print());

        // then
        actions.andExpect(status().isCreated())
                .andExpect(content().json(responseContent));
    }
}
