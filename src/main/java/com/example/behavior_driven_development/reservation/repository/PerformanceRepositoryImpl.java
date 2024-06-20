package com.example.behavior_driven_development.reservation.repository;

import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.dto.PerformanceResponseDto;
import com.example.behavior_driven_development.reservation.mapper.PerformanceMapper;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.persistence.performance.repository.PerformanceEntityJpaRepository;
import com.example.behavior_driven_development.reservation.persistence.performance.repository.PerformanceEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PerformanceRepositoryImpl implements PerformanceRepository {
    private final PerformanceEntityJpaRepository performanceEntityJpaRepository;
    private final PerformanceEntityRepository performanceEntityRepository;
    private final PerformanceMapper performanceMapper;

    @Override
    public Page<PerformanceResponseDto> findPerformances(Pageable pageable) {
        return performanceEntityRepository.findPerformances(pageable);
    }

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
