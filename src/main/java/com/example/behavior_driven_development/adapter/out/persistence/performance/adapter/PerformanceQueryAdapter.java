package com.example.behavior_driven_development.adapter.out.persistence.performance.adapter;

import com.example.behavior_driven_development.adapter.out.persistence.performance.repository.PerformanceEntityRepository;
import com.example.behavior_driven_development.application.port.out.PerformanceFindOutPort;
import com.example.behavior_driven_development.domain.PerformanceInventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class PerformanceQueryAdapter implements PerformanceFindOutPort {
    private final PerformanceEntityRepository performanceEntityRepository;

    @Override
    public PerformanceInventory findByIdAndReservationDate(long performanceId, LocalDate reservationDate) {
        return performanceEntityRepository.findByIdAndReservationDate(performanceId, reservationDate)
                .orElseThrow(() -> new NoSuchElementException("공연 정보를 찾을 수 없습니다."));
    }
}
