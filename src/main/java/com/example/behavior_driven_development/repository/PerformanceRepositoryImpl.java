package com.example.behavior_driven_development.repository;

import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.persistence.performance.repository.PerformanceEntityJpaRepository;
import com.example.behavior_driven_development.persistence.performance.repository.PerformanceEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class PerformanceRepositoryImpl implements PerformanceRepository {
    private final PerformanceEntityJpaRepository performanceEntityJpaRepository;
    private final PerformanceEntityRepository performanceEntityRepository;
    private final PerformanceMapper performanceMapper;

    @Override
    public Performance findPerformance(long performanceId) {
        PerformanceEntity findPerformanceEntity = performanceEntityRepository.findPerformance(performanceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "공연 정보를 찾을 수 없습니다."));
        return performanceMapper.toDomain(findPerformanceEntity);
    }

    @Override
    public void save(String performanceName) {
        PerformanceEntity newPerformanceEntity = performanceMapper.toEntity(performanceName);
        performanceEntityJpaRepository.save(newPerformanceEntity);
    }
}
