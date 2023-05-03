package org.unse.eventos.entidad;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(description = "Entidad Evento")
public class Evento {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	public Evento(Long id, String tipo, Integer cantVictimas, String[] autoridades, String areaInfluencia,
			String ubicacionEvento, LocalDateTime tiempoInicio, LocalDateTime tiempoFin, String ubiLatitud,
			String ubiLongitud) {
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
	}
	
	
	
}
