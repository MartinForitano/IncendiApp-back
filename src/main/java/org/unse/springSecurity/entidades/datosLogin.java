package org.unse.springSecurity.entidades;

public class datosLogin {

	private String nombre, contrasenia;

	public datosLogin(String nombre, String contrasenia) {
		super();
		this.setNombre(nombre);
		this.setContrasenia(contrasenia);
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
