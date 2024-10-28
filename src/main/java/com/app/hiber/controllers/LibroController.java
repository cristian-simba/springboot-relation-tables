package com.app.hiber.controllers;

import com.app.hiber.entities.Autor;
import com.app.hiber.entities.Categoria;
import com.app.hiber.entities.Libro;
import com.app.hiber.repositories.AutorRepository;
import com.app.hiber.repositories.CategoriaRepository;
import com.app.hiber.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Libro> listarLibros(){
       return libroRepository.findAll();
    }

    @Autowired
    AutorRepository autorRepository;

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro){
        Categoria categoria = categoriaRepository.findById(libro.getCategoria().getId()).orElseThrow(() -> new RuntimeException("No existe Categoria"));
        libro.setCategoria(categoria);
        Set<Autor> autoresPersistidos = new HashSet<>();
        for (Autor autor : libro.getAutores()) {
            Autor autorPersistido = autorRepository.findById(autor.getId())
                    .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
            autoresPersistidos.add(autorPersistido);
        }
        libro.setAutores(autoresPersistidos);
        return libroRepository.save(libro);
    }

    @PutMapping("/{id}")
    public Libro actualizarLibro(@PathVariable Long id, @RequestBody Libro libroDetalles){
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        libro.setTitulo(libroDetalles.getTitulo());
        libro.setCategoria(libroDetalles.getCategoria());
        return libroRepository.save(libro);
    }

    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id){
        libroRepository.deleteById(id);
    }
}
