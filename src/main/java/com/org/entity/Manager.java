package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Manager {
    @Id
    @GeneratedValue
    private Integer id;

    private String fullName;

    private String email;

    private String password;

    @OneToOne(
            fetch =
                    FetchType
                            .LAZY,

            cascade =
                    CascadeType
                            .ALL
    )
    private Branch branch;
}
