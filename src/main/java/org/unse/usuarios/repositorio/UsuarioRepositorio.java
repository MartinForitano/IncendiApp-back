package org.unse.usuarios.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.unse.usuarios.entidad.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	
	// Creamos la query para poder buscar por nombre de usuario
	  @Query("select u from Usuario u where u.nombre = ?1")
	  Optional<Usuario> buscarPorNombre(String nombreUsuario);
	
	
}
