package com.example.behavior_driven_development.adapter.out.persistence.inventory.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InventoryRepositoryImpl implements InventoryRepository {
    private final JPAQueryFactory jpaQueryFactory;
}
