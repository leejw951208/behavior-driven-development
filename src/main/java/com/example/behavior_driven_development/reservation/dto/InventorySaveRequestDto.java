package com.example.behavior_driven_development.reservation.dto;

import java.time.LocalDate;

public record InventorySaveRequestDto(int quantity, LocalDate reservationDate) {
}
