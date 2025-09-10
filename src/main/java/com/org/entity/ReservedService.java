package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ReservedService {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Service service;

    @ManyToOne
    @JoinTable(
            name =
                    "card_id"
    )
    private Cart cart;

    private Integer adults;

    private Integer children;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private String specialDetails;
}
