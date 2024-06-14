package com.example.behavior_driven_development.dto;

import java.time.LocalDate;

public record ReservationSaveRequestDto(String customerName, LocalDate reservationDate) {
}
