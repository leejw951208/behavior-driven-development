package com.example.behavior_driven_development.adapter.out.persistence.performance.adapter;

import com.example.behavior_driven_development.adapter.out.persistence.performance.repository.PerformanceEntityRepository;
import com.example.behavior_driven_development.application.port.out.PerformanceFindPort;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.PerformanceInventory;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class PerformanceQueryAdapter implements PerformanceFindPort {
    private final PerformanceEntityRepository performanceEntityRepository;

    @Override
    public PerformanceInventory findByIdAndReservationDate(long performanceId, LocalDate reservationDate) {
        return performanceEntityRepository.findByIdAndReservationDate(performanceId, reservationDate)
                .orElseThrow(() -> new NoSuchElementException("공연 정보를 찾을 수 없습니다."));
    }
}
