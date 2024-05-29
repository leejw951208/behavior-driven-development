package com.example.behavior_driven_development.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReserve {
    private Performance performance;
    private String customerName;
    private LocalDate reserveDate;
}
