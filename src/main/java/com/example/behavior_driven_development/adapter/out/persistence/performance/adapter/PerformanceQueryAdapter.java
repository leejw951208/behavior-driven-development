package com.example.behavior_driven_development.adapter.out.persistence.performance.adapter;

import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.repository.PerformanceEntityRepository;
import com.example.behavior_driven_development.application.port.out.PerformanceFindOutPort;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.PerformanceInventory;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class PerformanceQueryAdapter implements PerformanceFindOutPort {
    private final PerformanceEntityRepository performanceEntityRepository;
    private final PerformanceMapper performanceMapper;

    @Override
    public PerformanceInventory findByIdAndReservationDate(long performanceId, LocalDate reservationDate) {
        return performanceEntityRepository.findByIdAndReservationDate(performanceId, reservationDate)
                .orElseThrow(() -> new NoSuchElementException("공연 정보를 찾을 수 없습니다."));
    }

    @Override
    public Performance findPerformance(long performanceId) {
        PerformanceEntity findPerformanceEntity = performanceEntityRepository.findById(performanceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "공연 정보를 찾을 수 없습니다."));
        return performanceMapper.toDomain(findPerformanceEntity);
    }
}
