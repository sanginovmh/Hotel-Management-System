package com.org.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    @JoinColumn(
            nullable =
                    false
    )
    private Customer customer;

    @Column(
            unique =
                    true
    )
    private String number;

    private String expDate;

    private String  cvv;
}
