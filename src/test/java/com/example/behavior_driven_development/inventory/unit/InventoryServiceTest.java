package com.example.behavior_driven_development.inventory.unit;

import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.reservation.domain.InventorySave;
import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.dto.InventorySaveRequestDto;
import com.example.behavior_driven_development.reservation.mapper.InventoryMapper;
import com.example.behavior_driven_development.reservation.repository.InventoryRepository;
import com.example.behavior_driven_development.reservation.repository.PerformanceRepository;
import com.example.behavior_driven_development.reservation.service.impl.InventoryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest extends BaseTest {
    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private PerformanceRepository performanceRepository;

    @Mock
    private InventoryMapper inventoryMapper;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    @DisplayName("서비스 단위 테스트: 공연 좌석 정보 저장")
    public void saveInventory() {
        // given
        long performanceId = 1L;
        String performanceName = "홍길동전";
        Performance performance = Performance.builder().id(performanceId).performanceName(performanceName).build();

        int quantity1 = 1;
        int quantity2 = 2;
        LocalDate reservationDate1 = LocalDate.of(2024,6, 14);
        LocalDate reservationDate2 = LocalDate.of(2024,6, 15);
        List<InventorySaveRequestDto> requestDtos = List.of(
                new InventorySaveRequestDto(quantity1, reservationDate1),
                new InventorySaveRequestDto(quantity2, reservationDate2)
        );
        List<InventorySave> inventorySaves = List.of(
                InventorySave.builder().quantity(quantity1).reservationDate(reservationDate1).build(),
                InventorySave.builder().quantity(quantity2).reservationDate(reservationDate2).build()
        );

        given(performanceRepository.findPerformance(performanceId)).willReturn(performance);
        given(inventoryMapper.toDomains(requestDtos)).willReturn(inventorySaves);
        willDoNothing().given(inventoryRepository).saveAll(performance, inventorySaves);

        // when, then
        inventoryService.saveInventories(performanceId, requestDtos);
    }
}
