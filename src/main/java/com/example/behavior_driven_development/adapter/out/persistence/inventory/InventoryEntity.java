package com.example.behavior_driven_development.adapter.out.persistence.inventory;

import com.example.behavior_driven_development.adapter.out.persistence.performance.PerformanceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_inventory", uniqueConstraints = {
        @UniqueConstraint(
                name="contstraint",
                columnNames={"performance_id", "reservation_date"}
        )
})
@Entity
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private PerformanceEntity performanceEntity;

    @ColumnDefault("0")
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;
}
