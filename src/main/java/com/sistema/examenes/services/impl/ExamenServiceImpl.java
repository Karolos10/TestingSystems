package com.sistema.examenes.services.impl;

import com.sistema.examenes.entity.Examen;
import com.sistema.examenes.repository.ExamenRepository;
import com.sistema.examenes.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public Examen agregarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen actualizarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Set<Examen> obtenerExamenes() {
        return new LinkedHashSet<>(examenRepository.findAll());
    }

    @Override
    public Examen obtenerExamen(Long exameneId) {
        return examenRepository.findById(exameneId).get();
    }

    @Override
    public void eliminarExamen(Long exameneId) {
        Examen examen = new Examen();
        examen.setExameneId(exameneId);
        examenRepository.delete(examen);

    }
}
