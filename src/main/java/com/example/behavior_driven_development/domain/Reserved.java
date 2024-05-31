package com.example.behavior_driven_development.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Reserved {
    private long performanceId;
    private String performanceName;
    private String customerName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;
}
