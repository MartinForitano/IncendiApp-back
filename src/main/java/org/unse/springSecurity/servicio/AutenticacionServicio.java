package org.unse.springSecurity.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.unse.usuarios.repositorio.UsuarioRepositorio;

@Service
public class AutenticacionServicio implements UserDetailsService{

	@Autowired
	private UsuarioRepositorio repositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repositorio.findByNombre(username);
	}

}
