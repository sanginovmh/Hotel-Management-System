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
public class Manager {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
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
