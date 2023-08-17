package org.unse.eventos.entidad;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(description = "Entidad Evento")
public class Evento {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo;
	private Integer cantVictimas;
	private String[] autoridades;
	private String areaInfluencia;
	private String ubicacionEvento;
	private LocalDateTime tiempoInicio;
	private LocalDateTime tiempoFin;
	private String ubiLatitud;
	private String ubiLongitud;
	private Boolean esVerificado = true;
	
	public Evento(Long id, String tipo, Integer cantVictimas, String[] autoridades, String areaInfluencia,
			String ubicacionEvento, LocalDateTime tiempoInicio, LocalDateTime tiempoFin, String ubiLatitud,
			String ubiLongitud, Boolean esverificado) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.cantVictimas = cantVictimas;
		this.autoridades = autoridades;
		this.areaInfluencia = areaInfluencia;
		this.ubicacionEvento = ubicacionEvento;
		this.tiempoInicio = tiempoInicio;
		this.tiempoFin = tiempoFin;
		this.ubiLatitud = ubiLatitud;
		this.ubiLongitud = ubiLongitud;
		this.esVerificado = esverificado;
	}
	
	public Evento() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCantVictimas() {
		return cantVictimas;
	}

	public void setCantVictimas(Integer cantVictimas) {
		this.cantVictimas = cantVictimas;
	}

	public String[] getAutoridades() {
		return autoridades;
	}

	public void setAutoridades(String[] autoridades) {
		this.autoridades = autoridades;
	}

	public String getAreaInfluencia() {
		return areaInfluencia;
	}

	public void setAreaInfluencia(String areaInfluencia) {
		this.areaInfluencia = areaInfluencia;
	}

	public String getUbicacionEvento() {
		return ubicacionEvento;
	}

	public void setUbicacionEvento(String ubicacionEvento) {
		this.ubicacionEvento = ubicacionEvento;
	}

	public LocalDateTime getTiempoInicio() {
		return tiempoInicio;
	}

	public void setTiempoInicio(LocalDateTime tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public LocalDateTime getTiempoFin() {
		return tiempoFin;
	}

	public void setTiempoFin(LocalDateTime tiempoFin) {
		this.tiempoFin = tiempoFin;
	}

	public String getUbiLatitud() {
		return ubiLatitud;
	}

	public void setUbiLatitud(String ubiLatitud) {
		this.ubiLatitud = ubiLatitud;
	}

	public String getUbiLongitud() {
		return ubiLongitud;
	}

	public void setUbiLongitud(String ubiLongitud) {
		this.ubiLongitud = ubiLongitud;
	}

	public Boolean getEsVerificado() {
		return esVerificado;
	}

	public void setEsVerificado(Boolean esVerificado) {
		this.esVerificado = esVerificado;
	}
		
}
