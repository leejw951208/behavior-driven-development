package com.example.behavior_driven_development.inventory.integration;

import com.example.behavior_driven_development.base.BaseIntegrationTest;
import com.example.behavior_driven_development.dto.InventorySaveRequestDto;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.persistence.performance.repository.PerformanceEntityJpaRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InventoryIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private PerformanceEntityJpaRepository performanceEntityJpaRepository;

    private PerformanceEntity savedPerformanceEntity;

    @BeforeEach
    public void beforeEach() {
        PerformanceEntity performanceEntity = PerformanceEntity.builder().performanceName("홍길동전").build();
        savedPerformanceEntity = performanceEntityJpaRepository.save(performanceEntity);
    }

    @Test
    @DisplayName("통합 테스트: 공연 좌석 정보 저장")
    public void saveInventory() throws Exception {
        // given
        long performanceId = savedPerformanceEntity.getId();
        int quantity1 = 1;
        int quantity2 = 2;
        LocalDate reservationDate1 = LocalDate.of(2024,6, 14);
        LocalDate reservationDate2 = LocalDate.of(2024,6, 15);
        List<InventorySaveRequestDto> requestDtos = List.of(
                new InventorySaveRequestDto(quantity1, reservationDate1),
                new InventorySaveRequestDto(quantity2, reservationDate2)
        );

        // when
        ResultActions actions = mockMvc.perform(post("/api/performances/{performanceId}/inventories", performanceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtos)))
                .andDo(print());

        // then
        actions.andExpect(status().isCreated())
                .andExpect(content().string("succeed"));
    }
}
