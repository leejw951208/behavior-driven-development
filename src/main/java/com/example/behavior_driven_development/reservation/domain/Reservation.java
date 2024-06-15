package com.example.behavior_driven_development.reservation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private long performanceId;
    private String performanceName;
    private LocalDate reservationDate;
}
