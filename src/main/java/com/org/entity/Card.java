package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Card {
    @Id
    @GeneratedValue
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
    private Integer number;

    private String expDate;

    private Integer cvv;
}
