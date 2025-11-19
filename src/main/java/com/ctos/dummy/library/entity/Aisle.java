package com.ctos.dummy.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_aisle")
public class Aisle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aisle_id")
    private int aisleId;

    @Column(name = "aisle_name", nullable = false)
    private String aisleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    private Library library;

    @ManyToMany()
    @JoinTable(name = "t_aisle_book",
    joinColumns = @JoinColumn(name = "aisle_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> aisBooks = new HashSet<>();
}
