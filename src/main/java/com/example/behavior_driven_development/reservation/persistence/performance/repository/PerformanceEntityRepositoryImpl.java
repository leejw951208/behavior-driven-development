package com.example.behavior_driven_development.reservation.persistence.performance.repository;

import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.dto.PerformanceResponseDto;
import com.example.behavior_driven_development.reservation.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.reservation.persistence.performance.QPerformanceEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.behavior_driven_development.reservation.persistence.performance.QPerformanceEntity.performanceEntity;

@Repository
@RequiredArgsConstructor
public class PerformanceEntityRepositoryImpl implements PerformanceEntityRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PerformanceEntity> findPerformance(long performanceId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(performanceEntity)
                        .where(performanceEntity.id.eq(performanceId))
                        .fetchOne()
        );
    }

    @Override
    public Page<PerformanceResponseDto> findPerformances(Pageable pageable) {
        List<PerformanceResponseDto> contents = queryFactory
                .select(Projections.constructor(PerformanceResponseDto.class,
                        performanceEntity.id,
                        performanceEntity.performanceName,
                        performanceEntity.createdDate
                ))
                .from(performanceEntity)
                .orderBy(performanceEntity.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(performanceEntity.count())
                .from(performanceEntity);

        return PageableExecutionUtils.getPage(contents, pageable, count::fetchOne);
    }
}
