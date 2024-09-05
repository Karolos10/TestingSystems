package com.sistema.examenes.controllers;

import com.sistema.examenes.entity.Examen;
import com.sistema.examenes.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/examen")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Examen> guardarExamen(@RequestBody Examen examen){
        return ResponseEntity.ok(examenService.agregarExamen(examen));
    }

    @PutMapping("/")
    public ResponseEntity<Examen> actualizarExamen(@RequestBody Examen examen){
        return ResponseEntity.ok(examenService.actualizarExamen(examen));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarExamenes(){
        return ResponseEntity.ok(examenService.obtenerExamenes());
    }

    @GetMapping("/{exameneId}")
    public Examen listarExamen(@PathVariable("exameneId") Long exameneId){
        return examenService.obtenerExamen(exameneId);
    }

    @DeleteMapping("/{exameneId}")
    public void eliminarExamen(@PathVariable("exameneId") Long exameneId){
        examenService.eliminarExamen(exameneId);
    }
}
