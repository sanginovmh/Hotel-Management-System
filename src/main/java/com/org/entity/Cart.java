package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(
            mappedBy =
                    "cart",

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
                    "cart",

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
}
