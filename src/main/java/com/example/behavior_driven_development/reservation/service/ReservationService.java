package com.example.behavior_driven_development.reservation.service;

import com.example.behavior_driven_development.reservation.dto.ReservationSaveRequestDto;
import com.example.behavior_driven_development.reservation.dto.ReservationResponseDto;

public interface ReservationService {
    ReservationResponseDto saveReservation(long performanceId, ReservationSaveRequestDto reservationRequestDto);
}
