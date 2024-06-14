package com.example.behavior_driven_development.repository;

import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.ReservationSave;
import com.example.behavior_driven_development.domain.Reservation;

public interface ReservationRepository {
    Reservation save(Performance performance, ReservationSave reservation);
}
