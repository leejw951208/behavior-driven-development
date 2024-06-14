package com.example.behavior_driven_development.performance.unit;

import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.common.config.JpaAuditingConfig;
import com.example.behavior_driven_development.config.TestQueryDslConfig;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.persistence.performance.repository.PerformanceEntityJpaRepository;
import com.example.behavior_driven_development.persistence.performance.repository.PerformanceEntityRepository;
import com.example.behavior_driven_development.repository.PerformanceRepository;
import com.example.behavior_driven_development.repository.PerformanceRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
public class PerformanceRepositoryTest extends BaseTest {
    @Mock
    private PerformanceEntityJpaRepository performanceEntityJpaRepository;

    @Mock
    private PerformanceMapper performanceMapper;

    @InjectMocks
    private PerformanceRepositoryImpl performanceRepository;

    @Test
    @DisplayName("리포지토리 단위 테스트: 공연 정보 저장")
    public void savePerformance() {
        // given
        String performanceName = "홍길동전";
        PerformanceEntity createdPerformanceEntity = PerformanceEntity.builder().performanceName(performanceName).build();
        PerformanceEntity savedPerformanceEntity = PerformanceEntity.builder().id(1L).performanceName(performanceName).build();

        given(performanceMapper.toEntity(performanceName)).willReturn(createdPerformanceEntity);
        given(performanceEntityJpaRepository.save(createdPerformanceEntity)).willReturn(savedPerformanceEntity);

        // when, then
        performanceRepository.save(performanceName);
    }
}
