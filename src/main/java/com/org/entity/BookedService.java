package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class BookedService {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Service service;

    @ManyToOne
    @JoinColumn(
            name =
                    "booking_id"
    )
    private Booking booking;

    private Integer adults;

    private Integer children;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private String specialDetails;
}
