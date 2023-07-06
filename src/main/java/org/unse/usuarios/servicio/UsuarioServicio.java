package org.unse.usuarios.servicio;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
				BCryptPasswordEncoder encryptador = new BCryptPasswordEncoder();
				u.setContrasenia(encryptador.encode(u.getContrasenia()));
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
		u.setContrasenia(desencriptarContrasenia(u.getContrasenia()));
		u.setNombre(desencriptarContrasenia(u.getNombre()));
		BCryptPasswordEncoder encryptador = new BCryptPasswordEncoder();
		Optional<Usuario> uDb = repositorio.buscarPorNombre(u.getNombre());
		uDb.get().setContrasenia(encryptador.encode(u.getContrasenia()));
		repositorio.save(uDb.get());
		return 1;
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
