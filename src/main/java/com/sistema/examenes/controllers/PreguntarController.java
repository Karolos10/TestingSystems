package com.sistema.examenes.controllers;

import com.sistema.examenes.entity.Examen;
import com.sistema.examenes.entity.Pregunta;
import com.sistema.examenes.services.ExamenService;
import com.sistema.examenes.services.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/pregunta")
public class PreguntarController {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Pregunta> guardarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));
    }

    @PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }

    @GetMapping("/examen/{exameneId}")
    public ResponseEntity<?> listarPreguntasDelExamen(@PathVariable("exameneId") Long exameneId){
        Examen examen = examenService.obtenerExamen(exameneId);
        Set<Pregunta> preguntas = examen.getPreguntas();

        List examenes = new ArrayList(preguntas);

        if(examenes.size() > Integer.parseInt(examen.getNumeroDePreguntas())){
            examenes = examenes.subList(0, Integer.parseInt(examen.getNumeroDePreguntas() + 1));
        }

        Collections.shuffle(examenes);
        return ResponseEntity.ok(examenes);
    }

    @GetMapping("/examen/todos/{exameneId}")
    public ResponseEntity<?> listarPreguntaDelExamenComoAdministrador(@PathVariable("exameneId") Long exameneId){
        Examen examen = new Examen();
        examen.setExameneId(exameneId);
        Set<Pregunta> preguntas = preguntaService.obtenerPreguntasDelExamen(examen);
        return ResponseEntity.ok(preguntas);

    }

    @GetMapping("/{preguntaId}")
    public Pregunta listarPreguntaId(@PathVariable("preguntaId") Long preguntaId){
        return preguntaService.obtenerPregunta(preguntaId);
    }

    @DeleteMapping("/{preguntaId}")
    public void eliminarPregunta(@PathVariable("preguntaId") Long preguntaId){
        preguntaService.eliminarPregunta(preguntaId);
    }

    @PostMapping("/evaluar-examen")
    public ResponseEntity<?> evaluarExamen(@RequestBody List<Pregunta> preguntas){
        double puntosMaximos = 0;
        Integer respuestasCorrectas = 0;
        Integer intentos = 0;

        for(Pregunta p : preguntas){
            Pregunta pregunta = this.preguntaService.listarPregunta(p.getPreguntaId());
            if(pregunta.getRespuesta().equals(p.getRespuesta())){
                respuestasCorrectas ++;
                double puntos = Double.parseDouble(preguntas.get(0).getExamen().getPuntosMaximos())/preguntas.size();
                puntosMaximos += puntos;
            }
            if(p.getRespuesta() != null){
                intentos ++;
            }
        }

        Map<String,Object> respuestas = new HashMap<>();
        respuestas.put("puntosMaximos",puntosMaximos);
        respuestas.put("respuestasCorrectas",respuestasCorrectas);
        respuestas.put("intentos",intentos);
        return ResponseEntity.ok(respuestas);
    }
}
