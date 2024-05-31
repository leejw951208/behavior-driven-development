package com.example.behavior_driven_development.adapter.in.web.dto;

import java.time.LocalDate;

public record ReservationRequestDto(
        long performanceId,
        String customerName,
        LocalDate reservationDate
) {
}
