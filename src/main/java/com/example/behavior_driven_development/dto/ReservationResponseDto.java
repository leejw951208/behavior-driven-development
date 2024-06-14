package com.example.behavior_driven_development.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ReservationResponseDto(
        long performanceId,
        String performanceName,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate reservationDate
) {
}
