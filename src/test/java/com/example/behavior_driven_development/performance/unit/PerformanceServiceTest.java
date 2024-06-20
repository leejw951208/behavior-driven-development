package com.example.behavior_driven_development.performance.unit;

import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.common.dto.PagingDto;
import com.example.behavior_driven_development.reservation.dto.PerformanceRequestDto;
import com.example.behavior_driven_development.reservation.dto.PerformanceResponseDto;
import com.example.behavior_driven_development.reservation.repository.PerformanceRepository;
import com.example.behavior_driven_development.reservation.service.impl.PerformanceServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
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

    @Test
    @DisplayName("서비스 단위 테스트: 공연 정보 목록 조회 페이징")
    public void findPerformancesPaging() {
        // given
        int page = 1;
        int perPage = 10;
        Pageable pageable = PageRequest.of(page - 1, perPage);
        List<PerformanceResponseDto> responseDtos = List.of(new PerformanceResponseDto(1L, "홍길동전", LocalDate.now()));
        Page<PerformanceResponseDto> performances = new PageImpl<>(responseDtos, pageable, 1);
        given(performanceRepository.findPerformances(pageable)).willReturn(performances);

        // when
        PagingDto<PerformanceResponseDto> findPerformances = performanceService.findPerformances(page, perPage);

        // then
        assertEquals(findPerformances.content().size(), performances.getContent().size());
        int size = findPerformances.content().size();
        for (int i = 0; i < size; i++) {
            assertEquals(findPerformances.content().get(i).id(), performances.getContent().get(i).id());
            assertEquals(findPerformances.content().get(i).performanceName(), performances.getContent().get(i).performanceName());
            assertEquals(findPerformances.content().get(i).createdDate(), performances.getContent().get(i).createdDate());
        }
    }
}
