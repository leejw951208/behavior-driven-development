package com.example.behavior_driven_development.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Performance {
    private long performanceId;
    private String performanceName;
    private LocalDate createdDate;
}
