package com.example.behavior_driven_development.reservation.dto;

import java.time.LocalDate;

public record ReservationSaveRequestDto(String customerName, LocalDate reservationDate) {
}
