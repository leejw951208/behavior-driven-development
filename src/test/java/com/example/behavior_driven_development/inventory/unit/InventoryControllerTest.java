package com.example.behavior_driven_development.inventory.unit;

import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.reservation.controller.InventoryController;
import com.example.behavior_driven_development.reservation.dto.InventorySaveRequestDto;
import com.example.behavior_driven_development.reservation.service.InventoryService;
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
import java.util.List;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InventoryService inventoryService;

    @Test
    @DisplayName("컨트롤러 단위 테스트: 공연 좌석 정보 저장")
    public void saveInventory() throws Exception {
        // given
        long performanceId = 1L;
        int quantity1 = 1;
        int quantity2 = 2;
        LocalDate reservationDate1 = LocalDate.of(2024,6, 14);
        LocalDate reservationDate2 = LocalDate.of(2024,6, 15);
        List<InventorySaveRequestDto> requestDtos = List.of(
                new InventorySaveRequestDto(quantity1, reservationDate1),
                new InventorySaveRequestDto(quantity2, reservationDate2)
        );

        willDoNothing().given(inventoryService).saveInventories(performanceId, requestDtos);

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
