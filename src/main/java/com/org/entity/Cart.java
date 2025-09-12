package com.org.entity;

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
public class Cart {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
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
    private List<ReservedRoom> reservedRooms = new ArrayList<>();

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
    private List<ReservedService> reservedServices = new ArrayList<>();

    public void addReservedRoom(ReservedRoom reservedRoom) {
        reservedRooms.add(reservedRoom);
        reservedRoom.setCart(this);
    }

    public void removeReservedRoom(ReservedRoom reservedRoom) {
        reservedRooms.remove(reservedRoom);
        reservedRoom.setCart(null);
    }

    public void addReservedService(ReservedService reservedService) {
        reservedServices.add(reservedService);
        reservedService.setCart(this);
    }

    public void removeReservedService(ReservedService reservedService) {
        reservedServices.remove(reservedService);
        reservedService.setCart(null);
    }
}
