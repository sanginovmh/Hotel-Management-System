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
public class Review {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne
    private Customer customer;

    @ManyToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    private Branch branch;

    private Integer star;

    private String text;

    private LocalDate date =
            LocalDate
                    .now();
}
