package org.unse.usuarios.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unse.usuarios.entidad.Usuario;
import org.unse.usuarios.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio {

	@Autowired
	private UsuarioRepositorio repositorio;

	/*
	 * RETORNA un entero: 1 Si el usuario se dio de alta exitosamente. 2 Si el
	 * usuario existe. 3 Si el usuario recibido tiene id (no deberia tener id en
	 * alta).
	 */
	public Integer altaUsuario(Usuario u) {
		if (u.getId() == null) {
			if (repositorio.buscarPorNombre(u.getNombre()).isEmpty()) {
				repositorio.save(u);
				return 1;
			} else {
				return 2;
			}
		} else {
			return 3;
		}
	}

	/*
	 * RETORNA un entero: 1 Si el usuario existe y es la contraseña correcta. 2 Si
	 * el usuario existe y la contraseña es incorrecta. 3 Si el usuario no existe.
	 */

	public Integer ingresar(Usuario u) {
		Optional<Usuario> uDb = repositorio.buscarPorNombre(u.getNombre());
		if (uDb.isPresent()) {
			if (u.getContrasenia().equals(uDb.get().getContrasenia())) {
				return 1;
			} else {
				return 2;
			}
		} else {
			return 3;
		}
	}

	/*
	 * RETORNA un entero: 1 Si el usuario existe y es la contraseña correcta, se
	 * borra al usuario. 2 Si el usuario existe y la contraseña es incorrecta, no se
	 * borra al usuario. 3 Si el usuario no existe.
	 */

	public Integer borrarUsuario(Usuario u) {
		Optional<Usuario> uDb = repositorio.buscarPorNombre(u.getNombre());
		if (uDb.isPresent()) {
			if (u.getContrasenia().equals(uDb.get().getContrasenia())) {
				repositorio.delete(uDb.get());
				return 1;
			} else {
				return 2;
			}
		} else {
			return 3;
		}
	}

	/*
	 * RETORNA un entero: 1 Si el usuario existe y es la contraseña correcta, se
	 * cambia la contraseña.
	 */

	public Integer cambiarContraseniaUsuario(Usuario u) {
		Optional<Usuario> uDb = repositorio.buscarPorNombre(u.getNombre());
		uDb.get().setContrasenia(u.getContrasenia());
		repositorio.save(uDb.get());
		return 1;
	}
}
