package com.example.behavior_driven_development.performance.integration;

import com.example.behavior_driven_development.base.BaseIntegrationTest;
import com.example.behavior_driven_development.reservation.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.persistence.performance.repository.PerformanceEntityJpaRepository;
import com.example.behavior_driven_development.reservation.repository.PerformanceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PerformanceIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private PerformanceEntityJpaRepository performanceEntityJpaRepository;

    @Test
    @DisplayName("통합 테스트: 공연 정보 저장")
    public void savePerformance() throws Exception {
        // given
        String performanceName = "홍길동전";
        PerformanceRequestDto requestDto = new PerformanceRequestDto(performanceName);

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
    public void findPerformances() throws Exception {
        // given
        String performanceName = "홍길동전";
        PerformanceEntity newPerformanceEntity = PerformanceEntity.builder()
                .performanceName(performanceName)
                .build();
        PerformanceEntity savedPerformanceEntity = performanceEntityJpaRepository.save(newPerformanceEntity);

        String page = "1";
        String perPage = "10";

        // when, then
        mockMvc.perform(get("/api/performances")
                        .param("page", page)
                        .param("perPage", perPage))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(savedPerformanceEntity.getId()))
                .andExpect(jsonPath("$.content[0].performanceName").value(savedPerformanceEntity.getPerformanceName()))
                .andExpect(jsonPath("$.content[0].createdDate").value(savedPerformanceEntity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .andDo(print());
    }
}
