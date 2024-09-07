package com.sistema.examenes.services.impl;

import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.repository.UsuarioRepository;
import com.sistema.examenes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {

        usuarioRepository.deleteById(usuarioId);

    }
}
