package com.example.behavior_driven_development.performance.unit;

import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.reservation.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.reservation.repository.PerformanceRepository;
import com.example.behavior_driven_development.reservation.service.impl.PerformanceServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
public class PerformanceServiceTest extends BaseTest {
    @Mock
    private PerformanceRepository performanceRepository;

    @InjectMocks
    private PerformanceServiceImpl performanceService;

    @Test
    @DisplayName("서비스 단위 테스트: 공연 정보 저장")
    public void savePerformance() {
        // given
        String performanceName = "홍길동전";
        PerformanceRequestDto requestDto = new PerformanceRequestDto(performanceName);

        willDoNothing().given(performanceRepository).save(requestDto.performanceName());

        // when, then
        performanceService.savePerformance(requestDto);
    }
}
