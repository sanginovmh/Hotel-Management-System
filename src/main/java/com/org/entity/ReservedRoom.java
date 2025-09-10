package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ReservedRoom {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Room room;

    @ManyToOne
    @JoinColumn(
            name =
                    "cart_id"
    )
    private Cart cart;

    private Integer adults;

    private Integer children;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private String specialDetails;
}
