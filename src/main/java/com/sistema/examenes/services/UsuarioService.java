package com.sistema.examenes.services;

import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.entity.UsuarioRol;

import java.util.Set;

public interface UsuarioService {

    public Usuario obtenerUsuario(String username);


    public void eliminarUsuario(Long usuarioId);
}
