package com.example.behavior_driven_development.reservation.unit;

import com.example.behavior_driven_development.controller.ReservationController;
import com.example.behavior_driven_development.dto.ReservationResponseDto;
import com.example.behavior_driven_development.dto.ReservationSaveRequestDto;
import com.example.behavior_driven_development.service.ReservationService;
import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.mapper.ReservationMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    private ReservationService reservationService;

    @Test
    @DisplayName("컨트롤러 단위 테스트: 공연 예약")
    public void saveReservation() throws Exception {
        // given
        long performanceId = 1L;
        String performanceName = "홍길동전";
        String customerName = "고길동";
        LocalDate reservationDate = LocalDate.now();

        ReservationSaveRequestDto requestDto = new ReservationSaveRequestDto(customerName, reservationDate);
        ReservationResponseDto responseDto = new ReservationResponseDto(performanceId, performanceName, reservationDate);

        given(reservationService.saveReservation(performanceId, requestDto)).willReturn(responseDto);

        // when
        ResultActions actions = mockMvc.perform(post("/api/performances/{performanceId}/reservations", performanceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print());

        // then
        actions.andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }
}
