package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaReposiotry extends JpaRepository<Categoria, Long> {
}
