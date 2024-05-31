package com.example.behavior_driven_development.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ReservationResponseDto(
        long performanceId,
        String performanceName,
        String customerName,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate reservationDate
) {
}
