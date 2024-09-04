package com.sistema.examenes.controllers;

import com.sistema.examenes.dto.LoginDto;
import com.sistema.examenes.dto.LoginResponde;
import com.sistema.examenes.dto.RegistroDto;
import com.sistema.examenes.entity.CustomUserDetails;
import com.sistema.examenes.entity.Rol;
import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.entity.UsuarioRol;
import com.sistema.examenes.services.AuthenticationService;
import com.sistema.examenes.services.impl.JwtService;
import com.sistema.examenes.services.impl.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/signup")
    public ResponseEntity<Usuario> register(@RequestBody RegistroDto registroDto) throws Exception {

        registroDto.setPerfil("default.png");


        Usuario registeredUser = authenticationService.signup(registroDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponde> authenticate(@RequestBody LoginDto loginDto) {
        CustomUserDetails authenticatedUser = authenticationService.authenticate(loginDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponde loginResponse = new LoginResponde(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }


}
