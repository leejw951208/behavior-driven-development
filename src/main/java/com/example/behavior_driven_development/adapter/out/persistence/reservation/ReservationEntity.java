package com.example.behavior_driven_development.adapter.out.persistence.reservation;

import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_reservation_info")
@Entity
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private PerformanceEntity performanceEntity;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;
}
