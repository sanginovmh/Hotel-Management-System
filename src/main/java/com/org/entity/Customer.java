package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

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
                            .LAZY
    )
    private List<Card> cards;
}
