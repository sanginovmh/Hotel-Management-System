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
    private List<ReservedRoom> reservedRooms;

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
    private List<ReservedService> reservedServices;

    @OneToOne
    private Payment payment;

    private BookingStatus status;
}
