package com.org.entity;

import com.org.interfaces.PhotoFolderable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room implements PhotoFolderable {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
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
                            .EAGER,

            orphanRemoval =
                    true,

            cascade =
                    CascadeType
                            .ALL
    )
    private PhotoFolder photoFolder;

    private String specialDetails;
}
