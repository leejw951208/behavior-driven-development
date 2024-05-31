package com.example.behavior_driven_development.adapter.in.web;

import com.example.behavior_driven_development.adapter.in.web.dto.ReservationRequestDto;
import com.example.behavior_driven_development.adapter.in.web.dto.ReservationResponseDto;
import com.example.behavior_driven_development.application.port.in.ReservationInPort;
import com.example.behavior_driven_development.domain.Reserved;
import com.example.behavior_driven_development.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationInPort reservationInPort;
    private final ReservationMapper reservationMapper;

    @PostMapping("/api/v1/reservations")
    public ResponseEntity<ReservationResponseDto> reservePerformance(@RequestBody ReservationRequestDto reservationRequestDto) {
        Reserved reserved = reservationInPort.reserve(reservationRequestDto);
        ReservationResponseDto responseDto = reservationMapper.toDto(reserved);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
