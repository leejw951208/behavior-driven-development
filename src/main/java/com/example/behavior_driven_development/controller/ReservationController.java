package com.example.behavior_driven_development.controller;

import com.example.behavior_driven_development.dto.ReservationSaveRequestDto;
import com.example.behavior_driven_development.dto.ReservationResponseDto;
import com.example.behavior_driven_development.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/api/performances/{performanceId}/reservations")
    public ResponseEntity<ReservationResponseDto> saveReservation(
            @PathVariable long performanceId,
            @RequestBody ReservationSaveRequestDto requestDto
    ) {
        return new ResponseEntity<>(reservationService.saveReservation(performanceId, requestDto), HttpStatus.CREATED);
    }
}
