package com.app.hiber.controllers;

import com.app.hiber.entities.Categoria;
import com.app.hiber.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria){
        return categoriaRepository.save(categoria);
    }
}
