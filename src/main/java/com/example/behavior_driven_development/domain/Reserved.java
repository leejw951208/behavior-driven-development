package com.example.behavior_driven_development.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserved {
    private long performanceId;
    private String performanceName;
    private String customerName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;
}
