package com.example.behavior_driven_development.config;

import com.example.behavior_driven_development.reservation.persistence.performance.repository.PerformanceEntityRepository;
import com.example.behavior_driven_development.reservation.persistence.performance.repository.PerformanceEntityRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestQueryDslConfig {
    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }

    @Bean
    public PerformanceEntityRepository performanceEntityRepository() {
        return new PerformanceEntityRepositoryImpl(jpaQueryFactory());
    }
}
