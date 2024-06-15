package com.example.behavior_driven_development.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    private long inventoryId;
    private int quantity;
    private LocalDate reservationDate;

    public void decreaseQuantity() {
        this.quantity -= 1;
    }
}
