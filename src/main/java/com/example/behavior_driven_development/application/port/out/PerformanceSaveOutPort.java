package com.example.behavior_driven_development.application.port.out;

import com.example.behavior_driven_development.domain.Inventory;
import com.example.behavior_driven_development.domain.Reservation;
import com.example.behavior_driven_development.domain.Reserved;

public interface PerformanceSaveOutPort {
    void save(String performanceName);
}
