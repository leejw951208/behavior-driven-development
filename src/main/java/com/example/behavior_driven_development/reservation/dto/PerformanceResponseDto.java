package com.example.behavior_driven_development.reservation.dto;

import java.time.LocalDate;

public record PerformanceResponseDto(long id, String performanceName, LocalDate createdDate) {
}
