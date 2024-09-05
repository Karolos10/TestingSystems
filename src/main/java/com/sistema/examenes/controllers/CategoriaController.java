package com.sistema.examenes.controllers;

import com.sistema.examenes.entity.Categoria;
import com.sistema.examenes.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/")
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria){
        Categoria categoriGuardada = categoriaService.agregarCategoria(categoria);
        return ResponseEntity.ok(categoriGuardada);
    }

    @GetMapping("/{categoriaId}")
    public Categoria listarCategoria(@PathVariable("categoriaId") Long categoriaId){
        return categoriaService.obtenerCategoria(categoriaId);
    }

    @GetMapping("/")
    public ResponseEntity<?> listarCategorias(){
        return ResponseEntity.ok(categoriaService.obtenerCategorias());
    }

    @PutMapping("/")
    public Categoria actualizarCategoria(@RequestBody Categoria categoria){
        return categoriaService.actualizarCategoria(categoria);
    }

    @DeleteMapping("/{categoriaId}")
    public void eliminarCategoria(@PathVariable("categoriaId") Long categoriaId){
        categoriaService.eliminarCategoria(categoriaId);
    }
}
