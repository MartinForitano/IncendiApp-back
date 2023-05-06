package org.unse.usuarios.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unse.usuarios.entidad.Usuario;
import org.unse.usuarios.servicio.UsuarioServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@RestController
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio servicio;

	
	
	public UsuarioControlador() {
	}

	@Operation(summary = "LogIn", description = "Chequeo de datos para Log In", tags = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Log In exitoso"),
			@ApiResponse(responseCode = "400", description = "Contraseña incorrecta"),
			@ApiResponse(responseCode = "404", description = "Usuario inexistente") })
	@GetMapping(produces = "application/json", consumes = "application/json", path = "/login/")
	public ResponseEntity<Usuario> login(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario") @RequestBody Usuario u) {
		switch (servicio.ingresar(u)) {
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

	@Operation(summary = "Alta de usuario", description = "Alta de los datos de un usuario", tags = "POST")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuario cargado"),
			@ApiResponse(responseCode = "400", description = "El usuario ya existe"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	@PostMapping(produces = "application/json", consumes = "application/json", path = "/altausuario")
	public ResponseEntity<Usuario> altaUsuario(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario a registrar") @RequestBody Usuario u) {
		switch (servicio.altaUsuario(u)) {
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
	@DeleteMapping(produces = "application/json", consumes = "application/json", path = "/bajausuario")
	public ResponseEntity<Usuario> bajaUsuario(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario a eliminar") @RequestBody Usuario u) {
		switch (servicio.borrarUsuario(u)) {
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
	@PutMapping(produces = "application/json", consumes = "application/json", path = "/contraseniausuario")
	public ResponseEntity<Usuario> cambiarContraseniaUsuario(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario para cambiar contraseña") @RequestBody Usuario u) {
		switch (servicio.cambiarContraseniaUsuario(u)) {
		case 1:
			return ResponseEntity.ok().build();
		default:
			return ResponseEntity.internalServerError().build();
		}
	}
	

	

	@Bean
	public OpenAPI openApiConfiguration() {
		return new OpenAPI().info(new Info().title("IncendiApp - Usuario").version("0.0.4")
				.description("IncendiApp API documentation using Open APi y Spring Doc")
				.termsOfService("http:/swagger.io/terms/")
				.license(new License().name("MIT / Apache 2.0").url("http://springdoc.org"))
				.contact(new Contact().name("Martin Tomas Foritano").email("martin.foritano.11@gmail.com")
						.url("https://www.linkedin.com/in/martin-tomas-foritano-609303153/")));
	}

}
