package com.org.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservedService {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
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
