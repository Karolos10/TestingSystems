package com.sistema.examenes.services.impl;

import com.sistema.examenes.entity.Categoria;
import com.sistema.examenes.repository.CategoriaReposiotry;
import com.sistema.examenes.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaReposiotry categoriaReposiotry;
    @Override
    public Categoria agregarCategoria(Categoria categoria) {
        return categoriaReposiotry.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        return categoriaReposiotry.save(categoria);
    }

    @Override
    public Set<Categoria> obtenerCategorias() {
        return new LinkedHashSet<>(categoriaReposiotry.findAll());
    }

    @Override
    public Categoria obtenerCategoria(Long categoriaId) {
        return categoriaReposiotry.findById(categoriaId).get();
    }

    @Override
    public void eliminarCategoria(Long categoriaId) {
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        categoriaReposiotry.delete(categoria);

    }
}
