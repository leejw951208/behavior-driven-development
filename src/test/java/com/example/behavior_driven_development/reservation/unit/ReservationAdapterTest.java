package com.example.behavior_driven_development.reservation.unit;

import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import com.example.behavior_driven_development.adapter.out.persistence.performance.repository.PerformanceEntityRepository;
import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntity;
import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntityCommandAdapter;
import com.example.behavior_driven_development.adapter.out.persistence.reservation.ReservationEntityJpaRepository;
import com.example.behavior_driven_development.base.BaseTest;
import com.example.behavior_driven_development.domain.Performance;
import com.example.behavior_driven_development.domain.Reservation;
import com.example.behavior_driven_development.domain.Reserved;
import com.example.behavior_driven_development.mapper.PerformanceMapper;
import com.example.behavior_driven_development.mapper.ReservationMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ReservationAdapterTest extends BaseTest {
    @Mock
    private ReservationEntityJpaRepository reservationEntityJpaRepository;

    @Mock
    private PerformanceMapper performanceMapper;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationEntityCommandAdapter reservationEntityCommandAdapter;

    @Test
    @DisplayName("어댑터 단위 테스트: 공연 예약")
    public void reservePerformance() {
        // given
        LocalDate date = LocalDate.now();

        PerformanceEntity performanceEntity = PerformanceEntity.builder().id(1L).performanceName("홍길동전").createdDate(date).build();
        ReservationEntity reservationEntity = ReservationEntity.builder().customerName("홍길동").performanceEntity(performanceEntity).reservationDate(date).build();
        ReservationEntity savedReservationEntity = ReservationEntity.builder().id(1L).customerName("홍길동").performanceEntity(performanceEntity).reservationDate(date).build();

        Performance performance = Performance.builder().performanceId(1L).performanceName("홍길동전").createdDate(date).build();
        Reservation reservation = Reservation.builder().performance(performance).reservationDate(date).customerName("고길동").quantity(1).build();
        Reserved reserved = Reserved.builder().performanceId(1L).performanceName("홍길동전").customerName("고길동").reservationDate(date).build();

        given(performanceMapper.toEntity(reservation.getPerformance())).willReturn(performanceEntity);
        given(reservationMapper.toEntity(performanceEntity, reservation.getCustomerName(), reservation.getReservationDate())).willReturn(reservationEntity);
        given(reservationEntityJpaRepository.save(reservationEntity)).willReturn(savedReservationEntity);
        given(reservationMapper.toDomain(savedReservationEntity)).willReturn(reserved);

        // when
        Reserved response = reservationEntityCommandAdapter.save(reservation);

        // then
        assertNotNull(response);
        assertEquals(response, reserved);
        assertEquals(response.getPerformanceId(), reserved.getPerformanceId());
        assertEquals(response.getPerformanceName(), reserved.getPerformanceName());
        assertEquals(response.getReservationDate(), reserved.getReservationDate());
        assertEquals(response.getCustomerName(), reserved.getCustomerName());
    }
}