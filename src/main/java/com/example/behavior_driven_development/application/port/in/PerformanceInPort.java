package com.example.behavior_driven_development.application.port.in;

import com.example.behavior_driven_development.adapter.in.web.dto.PerformanceRequestDto;

public interface PerformanceInPort {
    void register(String performanceName);
}
