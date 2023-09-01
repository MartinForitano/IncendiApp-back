package org.unse.usuarios.entidad;

public class DTOusuario {

	private String nombre, contrasenia;
	
	private Integer tipoUsuario;

	public DTOusuario(String nombre, String contrasenia, Integer tipousuario) {
		super();
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.tipoUsuario = tipousuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Integer getTipousuario() {
		return tipoUsuario;
	}

	public void setTipousuario(Integer tipousuario) {
		this.tipoUsuario = tipousuario;
	}
	
}
