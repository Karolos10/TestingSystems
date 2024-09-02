package com.sistema.examenes.services;

import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.entity.UsuarioRol;

import java.util.Set;

public interface UsuarioService {

    public Usuario saveUser(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
}
