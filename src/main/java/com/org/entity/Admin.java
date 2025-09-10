package com.org.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Admin {
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
}
