package com.org.entity;

import com.org.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    private Customer customer;

    @OneToMany(
            mappedBy =
                    "booking",

            fetch =
                    FetchType
                            .LAZY,

            orphanRemoval =
                    true,

            cascade =
                    CascadeType
                            .ALL
    )
    private List<BookedRoom> bookedRooms;

    @OneToMany(
            mappedBy =
                    "booking",

            fetch =
                    FetchType
                            .LAZY,

            orphanRemoval =
                    true,

            cascade =
                    CascadeType
                            .ALL
    )
    private List<BookedService> bookedServices;

    @OneToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    @JoinColumn(
            nullable =
                    false)
    private Payment payment;

    private BookingStatus status;
}
