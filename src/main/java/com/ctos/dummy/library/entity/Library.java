package com.ctos.dummy.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int libraryId;

    @Column(name = "library_name", nullable = false)
    private String libraryName;

    @OneToMany(mappedBy = "library")
    private List<Aisle> aislesList = new ArrayList<>();
}
