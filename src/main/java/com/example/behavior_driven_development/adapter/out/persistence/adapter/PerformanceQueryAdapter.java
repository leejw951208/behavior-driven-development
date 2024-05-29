package com.example.behavior_driven_development.adapter.out.persistence.adapter;

import com.example.behavior_driven_development.adapter.out.persistence.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.repository.PerformanceEntityRepository;
import com.example.behavior_driven_development.application.port.out.PerformanceFindPort;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceReservable;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class PerformanceQueryAdapter implements PerformanceFindPort {
    private final PerformanceEntityRepository performanceEntityRepository;
    private final PerformanceMapper performanceMapper;

    @Override
    public Performance findReservable(long performanceId, LocalDate reservableDate) {
        boolean isReserve = performanceEntityRepository.existsByIdAndReserveDate(performanceId, reservableDate);
        if (isReserve) {
            PerformanceEntity findPerformance = performanceEntityRepository.findById(performanceId)
                    .orElseThrow(() -> new NoSuchElementException("공연 정보를 찾을 수 없습니다."));
            return performanceMapper.toDomain(findPerformance);
        } else {
            throw new RuntimeException("공연 예약이 마감되었습니다.");
        }
    }
}
