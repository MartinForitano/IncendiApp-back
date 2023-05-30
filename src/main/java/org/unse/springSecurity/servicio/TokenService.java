package org.unse.springSecurity.servicio;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.unse.usuarios.entidad.Usuario;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenService {

	public String generarToken(Usuario usuario) throws JWTCreationException {
		Algorithm algorithm = Algorithm.HMAC256(generarClaveSecreta()); // deberia ser encriptada
		String token = JWT.create().withIssuer("Fori") // persona/empresa que emitio el JWT
				.withSubject(usuario.getNombre()) // nombre usuario
				.withClaim("id", usuario.getId()) // por si acaso se pide el id de usuario
				.withExpiresAt(crearFechaExpiracion()).sign(algorithm);
		return token;
	}

	private Instant crearFechaExpiracion() {
		return LocalDateTime.now().plusHours(10).toInstant(ZoneOffset.of("-03:00"));
	}

	private String generarClaveSecreta() {
		return new BCryptPasswordEncoder().encode("123456");
	}

	public String getSubject(String token) {
		DecodedJWT verifier = null;
		if (token == null) {
			throw new RuntimeException("Token invalido");
		}
		try {
			Algorithm algorithm = Algorithm.HMAC256(generarClaveSecreta());
			verifier = JWT.require(algorithm)
					.withIssuer("Fori")
					.build()
					.verify(token);
			verifier.getSubject();
		} catch (JWTVerificationException exception) {
			System.err.println(exception.toString());
		}
		if (verifier.getSubject() == null) {
			throw new RuntimeException("Verificador invalido");
		}
		return verifier.getSubject();
	}

}
