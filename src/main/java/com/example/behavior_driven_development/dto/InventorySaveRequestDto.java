package com.example.behavior_driven_development.dto;

import java.time.LocalDate;

public record InventorySaveRequestDto(int quantity, LocalDate reservationDate) {
}
