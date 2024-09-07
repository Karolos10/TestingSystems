package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preguntaId;

    @Column(length = 5000)
    private String contenidoPregunta;

    private String image;

    private String opcion1;

    private String opcion2;

    private String opcion3;

    private String opcion4;

    @Transient
    private String respuesta;

    @ManyToOne(fetch = FetchType.EAGER)
    private Examen examen;

    public Pregunta() {
    }

    public Pregunta(Long preguntaId,
                    String contenidoPregunta,
                    String image,
                    String opcion1,
                    String opcion2,
                    String opcion3,
                    String opcion4,
                    String respuesta,
                    Examen examen) {
        this.preguntaId = preguntaId;
        this.contenidoPregunta = contenidoPregunta;
        this.image = image;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.respuesta = respuesta;
        this.examen = examen;
    }

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getContenidoPregunta() {
        return contenidoPregunta;
    }

    public void setContenidoPregunta(String contenidoPregunta) {
        this.contenidoPregunta = contenidoPregunta;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
}
