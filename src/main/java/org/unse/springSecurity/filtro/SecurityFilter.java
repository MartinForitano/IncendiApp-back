package org.unse.springSecurity.filtro;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.unse.springSecurity.servicio.TokenService;
import org.unse.usuarios.entidad.Usuario;
import org.unse.usuarios.repositorio.UsuarioRepositorio;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//Obtenemos el token del header de la peticion
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null) {
			String token = authHeader.replace("Bearer ", "");
			String subject = tokenService.getSubject(token);
			if(subject != null) {
				//este token sera valido
				Optional<Usuario> usuario = usuarioRepositorio.buscarPorNombre(subject);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null, usuario.get().getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}

	
}
