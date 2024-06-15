package com.example.behavior_driven_development.performance.unit;

import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.reservation.controller.PerformanceController;
import com.example.behavior_driven_development.reservation.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.reservation.service.PerformanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PerformanceController.class)
public class PerformanceControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PerformanceService performanceService;

    @Test
    @DisplayName("컨트롤러 단위 테스트: 공연 정보 저장")
    public void savePerformance() throws Exception {
        // given
        String performanceName = "홍길동전";
        PerformanceRequestDto requestDto = new PerformanceRequestDto(performanceName);

        willDoNothing().given(performanceService).savePerformance(requestDto);

        // when
        ResultActions actions = mockMvc.perform(post("/api/performances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print());

        // then
        actions.andExpect(status().isCreated())
                .andExpect(content().string("succeed"));
    }
}
