package org.unse.springSecurity.entidades;

public class DTOToken {

	private String status_code, Token;

	
	
	public DTOToken(String status_code, String token) {
		super();
		this.status_code = status_code;
		Token = token;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	
}
