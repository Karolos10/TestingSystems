package com.sistema.examenes.services;

import com.sistema.examenes.dto.LoginDto;
import com.sistema.examenes.dto.RegistroDto;
import com.sistema.examenes.entity.CustomUserDetails;
import com.sistema.examenes.entity.Rol;
import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.entity.UsuarioRol;
import com.sistema.examenes.repository.RolRepository;
import com.sistema.examenes.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;

    private final RolRepository rolRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public Usuario signup(RegistroDto registroDto)throws Exception{
        Optional<Usuario> result = usuarioRepository.findByUsername(registroDto.getUsername());

        if(result.isEmpty()){
            Usuario usuario = new Usuario();
            usuario.setUsername(registroDto.getUsername());
            usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));
            usuario.setNombre(registroDto.getNombre());
            usuario.setApellidos(registroDto.getApellidos());
            usuario.setEmail(registroDto.getEmail());
            usuario.setTelefono(registroDto.getTelefono());
            usuario.setPerfil(registroDto.getPerfil());

            Rol rol = rolRepository.findById(2L).orElseThrow(() -> new RuntimeException("Rol no encontrado"));

            Set<UsuarioRol> usuarioRoles = new HashSet<>();
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setUsuario(usuario);
            usuarioRol.setRol(rol);
            usuarioRoles.add(usuarioRol);

            usuario.setUsuarioRoles(usuarioRoles);



            return usuarioRepository.save(usuario);

        }else{
            throw new Exception("El usuario ya se encuentra registrado!");
        }
    }

    public CustomUserDetails authenticate(LoginDto loginDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        return usuarioRepository.findByUsername(loginDto.getUsername())
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
