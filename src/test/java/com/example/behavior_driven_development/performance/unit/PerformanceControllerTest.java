package com.example.behavior_driven_development.performance.unit;

import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.common.dto.PagingDto;
import com.example.behavior_driven_development.reservation.controller.PerformanceController;
import com.example.behavior_driven_development.reservation.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.reservation.dto.PerformanceResponseDto;
import com.example.behavior_driven_development.reservation.service.PerformanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void findPerformancesPaging() throws Exception {
        // given
        int page = 1;
        int perPage = 10;
        Pageable pageable = PageRequest.of(page - 1, perPage);

        long performanceId = 1L;
        String performanceName = "홍길동전";
        LocalDate createdDate = LocalDate.now();
        List<PerformanceResponseDto> responseDtos = List.of(new PerformanceResponseDto(performanceId, performanceName, createdDate));
        PagingDto<PerformanceResponseDto> performances = new PagingDto<>(new PageImpl<>(responseDtos, pageable, 1));

        given(performanceService.findPerformances(page, perPage)).willReturn(performances);

        // when, then
        mockMvc.perform(get("/api/performances")
                        .param("page", "1")
                        .param("perPage", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(performanceId))
                .andExpect(jsonPath("$.content[0].performanceName").value(performanceName))
                .andExpect(jsonPath("$.content[0].createdDate").value(createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .andDo(print());

    }
}
