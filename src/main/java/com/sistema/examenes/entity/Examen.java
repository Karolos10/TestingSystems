package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "examenes")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exameneId;

    private String titulo;

    private String descripcion;

    private String puntosMaximos;

    private String numeroDePreguntas;

    private boolean activo=false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Pregunta> preguntas = new HashSet<>();

    public Examen() {
    }

    public Examen(Long exameneId,
                  String titulo,
                  String descripcion,
                  String puntosMaximos,
                  String numeroDePreguntas,
                  boolean activo,
                  Categoria categoria,
                  Set<Pregunta> preguntas) {
        this.exameneId = exameneId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.puntosMaximos = puntosMaximos;
        this.numeroDePreguntas = numeroDePreguntas;
        this.activo = activo;
        this.categoria = categoria;
        this.preguntas = preguntas;
    }

    public Long getExameneId() {
        return exameneId;
    }

    public void setExameneId(Long exameneId) {
        this.exameneId = exameneId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPuntosMaximos() {
        return puntosMaximos;
    }

    public void setPuntosMaximos(String puntosMaximos) {
        this.puntosMaximos = puntosMaximos;
    }

    public String getNumeroDePreguntas() {
        return numeroDePreguntas;
    }

    public void setNumeroDePreguntas(String numeroDePreguntas) {
        this.numeroDePreguntas = numeroDePreguntas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Set<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
