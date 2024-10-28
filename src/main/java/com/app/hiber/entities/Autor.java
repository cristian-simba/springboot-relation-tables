package com.app.hiber.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "autor")
    String nombre;
    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Libro> libros = new HashSet<>();
}
