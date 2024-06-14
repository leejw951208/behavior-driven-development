package com.example.behavior_driven_development.service;

import com.example.behavior_driven_development.dto.ReservationSaveRequestDto;
import com.example.behavior_driven_development.dto.ReservationResponseDto;

public interface ReservationService {
    ReservationResponseDto saveReservation(long performanceId, ReservationSaveRequestDto reservationRequestDto);
}
