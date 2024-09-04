package com.sistema.examenes.controllers;

import com.sistema.examenes.dto.RegistroDto;
import com.sistema.examenes.entity.CustomUserDetails;
import com.sistema.examenes.entity.Rol;
import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.entity.UsuarioRol;
import com.sistema.examenes.services.UsuarioService;
import com.sistema.examenes.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserDetailsServiceImpl userDetailsService;

    public UsuarioController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /*@PostMapping("/post")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{

        usuario.setPerfil("default.png");

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);

        return usuarioService.saveUser(usuario, usuarioRoles);

    }*/

    /*@GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){

        return usuarioService.obtenerUsuario(username);

    }*/

    /*@GetMapping("/{username}")
    public ResponseEntity<CustomUserDetails> obtenerUsuario(@PathVariable("username") String username) {
        Usuario usuario = usuarioService.obtenerUsuario(username);
        if (usuario != null) {
            CustomUserDetails customUserDetails = new CustomUserDetails(usuario);
            return ResponseEntity.ok(customUserDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/{username}")
    public ResponseEntity<RegistroDto> obtenerUsuario(@PathVariable("username") String username) {
        Usuario usuario = usuarioService.obtenerUsuario(username);
        if (usuario != null) {
            RegistroDto responseDto = new RegistroDto();
            //responseDto(usuario.getId());
            responseDto.setUsuarioId(usuario.getId());
            responseDto.setPassword(passwordEncoder.encode(usuario.getPassword()));
            responseDto.setUsername(usuario.getUsername());
            responseDto.setNombre(usuario.getNombre());
            responseDto.setApellidos(usuario.getApellidos());
            responseDto.setEmail(usuario.getEmail());
            responseDto.setTelefono(usuario.getTelefono());
            responseDto.setPerfil(usuario.getPerfil());

            // Map roles
            List<String> roles = usuario.getUsuarioRoles().stream()
                    .map(role -> role.getRol().getNombre())
                    .collect(Collectors.toList());
            responseDto.setRoles(roles);

            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }
    /*@GetMapping("/usuario-actual")
    public Usuario obtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());

    }*/

    @GetMapping("/usuario-actual")
    public ResponseEntity<RegistroDto> obtenerUsuarioActual(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            // Obtener el usuario desde el UserDetailsService
            UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
            Usuario usuario = (Usuario) userDetails;

            // Crear y llenar el DTO con la información del usuario
            RegistroDto responseDto = new RegistroDto();
            responseDto.setUsuarioId(usuario.getId());
            responseDto.setPassword(passwordEncoder.encode(usuario.getPassword())); // Asegúrate de que esto es necesario
            responseDto.setUsername(usuario.getUsername());
            responseDto.setNombre(usuario.getNombre());
            responseDto.setApellidos(usuario.getApellidos());
            responseDto.setEmail(usuario.getEmail());
            responseDto.setTelefono(usuario.getTelefono());
            responseDto.setPerfil(usuario.getPerfil());

            // Mapear roles
            List<String> roles = usuario.getUsuarioRoles().stream()
                    .map(role -> role.getRol().getNombre())
                    .collect(Collectors.toList());
            responseDto.setRoles(roles);

            return ResponseEntity.ok(responseDto);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
