package org.unse.eventos.entidad;

import java.util.List;

public class DTOListadoGeneral {

	private String response_status;
	private List<DTOEVentoResponse> listaEventos;
	
	public DTOListadoGeneral(String response_status, List<DTOEVentoResponse> listaEventos) {
		super();
		this.response_status = response_status;
		this.listaEventos = listaEventos;
	}
	public String getResponse_status() {
		return response_status;
	}
	public void setResponse_status(String response_status) {
		this.response_status = response_status;
	}
	public List<DTOEVentoResponse> getListaEventos() {
		return listaEventos;
	}
	public void setListaEventos(List<DTOEVentoResponse> listaEventos) {
		this.listaEventos = listaEventos;
	}
	
}
