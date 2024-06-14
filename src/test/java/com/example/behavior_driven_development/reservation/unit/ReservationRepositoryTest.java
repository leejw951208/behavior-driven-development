package com.example.behavior_driven_development.reservation.unit;

import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.Reservation;
import com.example.behavior_driven_development.domain.ReservationSave;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import com.example.behavior_driven_development.mapper.ReservationMapper;
import com.example.behavior_driven_development.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.persistence.reservation.ReservationEntityJpaRepository;
import com.example.behavior_driven_development.repository.ReservationRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ReservationRepositoryTest extends BaseTest {
    @Mock
    private ReservationEntityJpaRepository reservationEntityJpaRepository;

    @Mock
    private PerformanceMapper performanceMapper;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationRepositoryImpl reservationRepository;

    @Test
    @DisplayName("리포지토리 단위 테스트: 공연 예약")
    public void saveReservation() {
        // given
        long performanceId = 1L;
        String performanceName = "홍길동전";
        LocalDate createdDate = LocalDate.now();
        Performance performance = Performance.builder().performanceId(performanceId).performanceName(performanceName).createdDate(createdDate).build();

        String customerName = "고길동";
        LocalDate reservationDate = LocalDate.now();
        ReservationSave reservationSave = ReservationSave.builder().customerName(customerName).reservationDate(reservationDate).build();

        PerformanceEntity performanceEntity = PerformanceEntity.builder().id(performanceId).performanceName(performanceName).createdDate(createdDate).build();
        ReservationEntity reservationEntity = ReservationEntity.builder().performanceEntity(performanceEntity).customerName(customerName).reservationDate(reservationDate).build();
        ReservationEntity savedReservationEntity = ReservationEntity.builder().id(1L).performanceEntity(performanceEntity).customerName(customerName).reservationDate(reservationDate).build();
        Reservation reservation = Reservation.builder().performanceId(performanceId).performanceName(performanceName).reservationDate(reservationDate).build();

        given(performanceMapper.toEntity(performance)).willReturn(performanceEntity);
        given(reservationMapper.toEntity(performanceEntity, reservationSave)).willReturn(reservationEntity);
        given(reservationEntityJpaRepository.save(reservationEntity)).willReturn(savedReservationEntity);
        given(reservationMapper.toDomain(savedReservationEntity)).willReturn(reservation);

        // when
        Reservation savedReservation = reservationRepository.save(performance, reservationSave);

        // then
        assertNotNull(savedReservation);
        assertEquals(savedReservation.getPerformanceId(), performanceId);
        assertEquals(savedReservation.getPerformanceName(), performanceName);
        assertEquals(savedReservation.getReservationDate(), reservationDate);
    }
}
