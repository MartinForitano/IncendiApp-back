package org.unse.springSecurity.controlador;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unse.springSecurity.entidades.DTOToken;
import org.unse.springSecurity.entidades.datosLogin;
import org.unse.springSecurity.servicio.TokenService;
import org.unse.usuarios.entidad.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class AuthenticacionController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenServicio;

	@Operation(summary = "LogIn usuario", description = "Chequeo de datos para Log In", tags = "POST")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Log In exitoso"),
			@ApiResponse(responseCode = "400", description = "Contraseña incorrecta"),
			@ApiResponse(responseCode = "404", description = "Usuario inexistente") })
	@PostMapping(produces = "application/json", consumes = "application/json", path = "/usuarios/login/")
	public ResponseEntity<DTOToken> login(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario") @RequestBody datosLogin u) {
		return autenticarUsuario(new Usuario(null, u.getContrasenia(), u.getNombre()));

	}

	public ResponseEntity<DTOToken> autenticarUsuario(Usuario usuario) {
		usuario.setContrasenia(desencriptarContrasenia(usuario.getContrasenia()));
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(usuario.getNombre(),
				usuario.getContrasenia());
		Authentication usuarioAutenticado = authenticationManager.authenticate(authToken);
		String JWTToken = tokenServicio.generarToken((Usuario) usuarioAutenticado.getPrincipal()); // obtener el usuario
																									// ya autenticado
		return ResponseEntity.ok(new DTOToken("Ok", JWTToken));
	}

	private String desencriptarContrasenia(String contrasenia) {
		String llave = "ClavePasajeContraseña";
		return desencriptar(llave, contrasenia);
	}

	private String desencriptar(String llave, String contrasenia) {
		try {
			SecretKeySpec keySpec = crearClave(llave);
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] cadena = Base64.getDecoder().decode(contrasenia);
			byte[] desencriptacion = cipher.doFinal(cadena);
			return new String(desencriptacion, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private SecretKeySpec crearClave(String llave) {
		try {
			byte[] cadena = llave.getBytes();
			MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
			cadena = mDigest.digest(cadena);
			cadena = Arrays.copyOf(cadena, 16);
			SecretKeySpec keySpec = new SecretKeySpec(cadena, "AES");
			return keySpec;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private SecretKey crearLlave() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom random = new SecureRandom(); // cryptograph. secure random
            keyGen.init(random);
            SecretKey secretKey = keyGen.generateKey();
            return secretKey;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
