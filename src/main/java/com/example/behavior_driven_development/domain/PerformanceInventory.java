package com.example.behavior_driven_development.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceInventory {
    private Performance performance;
    private Inventory inventory;
}
