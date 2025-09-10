package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    private Branch branch;

    private String number;

    private String type;

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
    private PhotoFolder photoFolders;

    private String specialDetails;
}
