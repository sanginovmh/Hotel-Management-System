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
public class PhotoFolder {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    @OneToMany(
            mappedBy =
                    "photoFolder",

            orphanRemoval =
                    true,

            fetch =
                    FetchType.EAGER,

            cascade =
                    CascadeType.ALL
    )
    private List<Photo> photos = new ArrayList<>();
}
