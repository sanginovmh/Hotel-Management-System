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
public class Customer {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(
            nullable =
                    false
    )
    private String firstName;

    @Column(
            nullable =
                    false
    )
    private String lastName;

    @Column(
            unique =
                    true,

            nullable =
                    false
    )
    private String phone;

    @Column(
            unique =
                    true,

            nullable =
                    false
    )
    private String email;

    @Column(
            nullable =
                    false
    )
    private String password;

    @OneToOne(
            fetch =
                    FetchType
                            .LAZY,

            orphanRemoval =
                    true,

            cascade =
                    CascadeType
                            .ALL
    )
    private Cart cart;

    @OneToMany(
            mappedBy =
                    "customer",

            fetch =
                    FetchType
                            .LAZY,

            orphanRemoval =
                    true,

            cascade =
                    CascadeType
                            .ALL
    )
    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
        card.setCustomer(this);
    }

    public void removeCard(Card card) {
        cards.remove(card);
        card.setCustomer(null);
    }
}
