package com.org.entity;

import com.org.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
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
    private List<BookedRoom> bookedRooms = new ArrayList<>();

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
    private List<BookedService> bookedServices = new ArrayList<>();

    @OneToOne(
            fetch =
                    FetchType
                            .LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            nullable =
                    false)
    private Payment payment;

    private BookingStatus status;
}
