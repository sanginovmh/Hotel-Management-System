package com.org.entity;

import com.org.interfaces.PhotoFolderable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Room implements PhotoFolderable {
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
    private PhotoFolder photoFolder;

    private String specialDetails;
}
