package com.sistema.examenes;

import com.sistema.examenes.entity.Rol;
import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.entity.UsuarioRol;
import com.sistema.examenes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SistemaExamenesBackendApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(SistemaExamenesBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Usuario usuario = new Usuario();

		usuario.setNombre("Karlos");
		usuario.setApellidos("Rodriguez");
		usuario.setUsername("karolos");
		usuario.setPassword("123456");
		usuario.setEmail("karolos@gmail.com");
		usuario.setTelefono("31245678");
		usuario.setPerfil("foto.png");

		Rol rol = new Rol();
		rol.setRolId(1L);
		rol.setNombre("ADMIN");

		Set<UsuarioRol> usuarioRoles = new HashSet<>();
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setRol(rol);
		usuarioRol.setUsuario(usuario);
		usuarioRoles.add(usuarioRol);

		Usuario usuarioGuardado = usuarioService.saveUser(usuario, usuarioRoles);
		System.out.println(usuarioGuardado.getUsername());*/
	}
}
