package com.example.behavior_driven_development.application.port.in;

import com.example.behavior_driven_development.domain.Reserved;

import java.time.LocalDate;

public interface ReservationUseCase {
    Reserved reserve(long performanceId, String customerName, LocalDate reservationDate);
}
