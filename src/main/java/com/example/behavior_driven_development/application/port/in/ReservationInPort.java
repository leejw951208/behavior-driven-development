package com.example.behavior_driven_development.application.port.in;

import com.example.behavior_driven_development.adapter.in.web.dto.ReservationRequestDto;
import com.example.behavior_driven_development.domain.Reserved;

import java.time.LocalDate;

public interface ReservationInPort {
    Reserved reserve(ReservationRequestDto reservationRequestDto);
}
