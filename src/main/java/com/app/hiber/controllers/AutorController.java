package com.app.hiber.controllers;

import com.app.hiber.entities.Autor;
import com.app.hiber.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    AutorRepository autorRepository;

    @GetMapping
    public List<Autor> listarAutores(){
        return autorRepository.findAll();
    }

    @PostMapping
    public Autor crearAutor(@RequestBody Autor autor){
        return autorRepository.save(autor);
    }

    @PutMapping("/{id}")
    public Autor actualizaAutor(@PathVariable Long id, @RequestBody Autor detalleAutor){
        Autor autor = autorRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro al autor"));
        autor.setNombre(detalleAutor.getNombre());
        return autorRepository.save(autor);
    }

    @DeleteMapping("/{id}")
    public void eliminarAutor(@PathVariable Long id){
        autorRepository.deleteById(id);
    }
}

