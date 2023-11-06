package org.unse.usuarios.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unse.usuarios.entidad.DTOusuario;
import org.unse.usuarios.entidad.Usuario;
import org.unse.usuarios.servicio.UsuarioServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio servicio;

	
	
	public UsuarioControlador() {
	}

	@Operation(summary = "Alta de usuario", description = "Alta de los datos de un usuario", tags = "POST")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuario cargado"),
			@ApiResponse(responseCode = "400", description = "El usuario ya existe"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@PostMapping(produces = "application/json", consumes = "application/json", path = "/usuarios/alta/")
	public ResponseEntity<Usuario> altaUsuario(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario a registrar") @RequestBody DTOusuario u) {
		switch (servicio.altaUsuario(new Usuario(null, u.getContrasenia(), u.getNombre(), 2))) {
		case 1:
			return ResponseEntity.ok().build();
		case 2:
			return ResponseEntity.badRequest().build();
		case 3:
			return ResponseEntity.internalServerError().build();
		default:
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@Operation(summary = "Baja de usuario", description = "Baja de los datos de un usuario", tags = "DELETE")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuario eliminado"),
			@ApiResponse(responseCode = "400", description = "Contraseña incorrecta"),
			@ApiResponse(responseCode = "404", description = "Usuario inexistente") })
	@DeleteMapping(produces = "application/json", consumes = "application/json", path = "/usuarios/baja/")
	public ResponseEntity<Usuario> bajaUsuario(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario a eliminar") @RequestBody DTOusuario u) {
		switch (servicio.borrarUsuario(new Usuario(null, u.getContrasenia(), u.getNombre(), u.getTipousuario()))) {
		case 1:
			return ResponseEntity.ok().build();
		case 2:
			return ResponseEntity.badRequest().build();
		case 3:
			return ResponseEntity.notFound().build();
		default:
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@Operation(summary = "Cambio de contraseña", description = "Cambio de contraseña de un usuario", tags = "PUT")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Contraseña cambiada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")})
	@PutMapping(produces = "application/json", consumes = "application/json", path = "/usuarios/actualizar/contrasenia/")
	public ResponseEntity<Usuario> cambiarContraseniaUsuario(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario para cambiar contraseña") @RequestBody DTOusuario u) {
		switch (servicio.cambiarContraseniaUsuario(new Usuario(null, u.getContrasenia(), u.getNombre(), null))) {
		case 1:
			return ResponseEntity.ok().build();
		default:
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@Operation(summary = "Obtener tipo", description = "Obtener tipo de usuario registrado", tags = "PUT")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo obtenido"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")})
	@PostMapping(produces = "application/json", consumes = "application/json", path = "/usuarios/tipousuario/")
	public ResponseEntity<Usuario> obtenerTipoUsuario(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario para obtener tipo") @RequestBody DTOusuario u) {
		 return ResponseEntity.ok(servicio.obtenerTipoUsuario(new Usuario(null, u.getContrasenia(), u.getNombre(), null)));
		}
	}


