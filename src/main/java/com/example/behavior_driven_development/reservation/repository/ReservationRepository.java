package com.example.behavior_driven_development.reservation.repository;

import com.example.behavior_driven_development.reservation.domain.Performance;
import com.example.behavior_driven_development.reservation.domain.ReservationSave;
import com.example.behavior_driven_development.reservation.domain.Reservation;

public interface ReservationRepository {
    Reservation save(Performance performance, ReservationSave reservation);
}
