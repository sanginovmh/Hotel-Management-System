package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Manager {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(
            nullable =
                    false
    )
    private String fullName;

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
            mappedBy =
                    "manager"
    )
    private Branch branch;
}
