package com.sistema.examenes.services.impl;

import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Buscar el usuario como un Optional
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);

        // Verificar si el usuario está presente
        Usuario usuario = optionalUsuario.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Devolver el usuario como UserDetails
        return usuario;
    }
}
