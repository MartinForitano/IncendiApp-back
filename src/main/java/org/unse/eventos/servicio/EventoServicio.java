package org.unse.eventos.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.unse.eventos.entidad.Evento;
import org.unse.eventos.repositorio.EventosRepositorio;

public class EventoServicio {

	@Autowired
	private EventosRepositorio repositorio;

	/*
	 * Devuelve una lista con todos los eventos
	 */

	public List<Evento> listaEventosGral() {
		return repositorio.findAll();
	}

	/*
	 * Devuelve una lista con todos los eventos 
	 * Recibe 1 string indicando ubicacionEvento, otro indicando el tipo, 1 fecha inicio y otra fecha fin
	 */

	public List<Evento> listaEventoFiltrada(String ubicacionEvento, String tipo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {
		if (fechaInicio == null && fechaFin == null) {
			if (ubicacionEvento.trim() == "" && tipo.trim() != "") {
				return buscarPorTipoSinFecha(tipo);
			} else {
				if (ubicacionEvento.trim() != "" && tipo.trim() == "") {
					return buscarPorUbicacionSinFecha(ubicacionEvento);
				} else {
					return listaEventosGral();
				}
			}
		} else {
			if (ubicacionEvento.trim() == "" && tipo.trim() != "") {
				return buscarPorTipoConFechas(tipo, fechaInicio, fechaFin);
			} else {
				if (ubicacionEvento.trim() != "" && tipo.trim() == "") {
					return buscarPorUbicacionConFechas(ubicacionEvento, fechaInicio, fechaFin);
				} else {
					return buscarPorFechas(fechaInicio, fechaFin);
				}
			}
		}
	}

	private List<Evento> buscarPorTipoSinFecha(String tipo) {
		return repositorio.buscarPorTipoSinFecha(tipo);
	}

	private List<Evento> buscarPorUbicacionSinFecha(String zona) {
		return repositorio.buscarPorUbicacionSinFecha(zona);
	}

	private List<Evento> buscarPorTipoConFechas(String tipo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return repositorio.buscarPorTipoConFecha(tipo, fechaInicio, fechaFin);
	}

	private List<Evento> buscarPorUbicacionConFechas(String zona, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return repositorio.buscarPorTipoConFecha(zona, fechaInicio, fechaFin);
	}

	private List<Evento> buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return repositorio.buscarPorFechas(fechaInicio, fechaFin);
	}

	/*
	 * RETORNA un entero: 
	 * 1 Si el evento se dio de alta exitosamente. 
	 * 2 Si el evento recibido tiene id (no deberia tener id en alta).
	 */

	public Integer altaEvento(Evento e) {
		if (e.getId() == null) {
			repositorio.save(e);
			return 1;
		} else {
			return 2;
		}
	}

	/*
	 * RETORNA un entero: 
	 * 1 Si el evento existe y se borra el evento. 
	 * 2 Si el evento no existe.
	 */

	public Integer borrarEvento(Long id) {
		Optional<Evento> eDb = repositorio.findById(id);
		if (eDb.isPresent()) {
			repositorio.delete(eDb.get());
			return 1;
		} else {
			return 2;
		}
	}

	/*
	 * RETORNA un entero: 
	 * 1 Si el evento existe, se cambian los datos.
	 * 2 Si el evento no existe.
	 */

	public Integer cambiarContraseniaUsuario(Evento e) {
		Optional<Evento> eDb = repositorio.findById(e.getId());
		if (eDb.isPresent()) {
			eDb.get().setAreaInfluencia(e.getAreaInfluencia());
			eDb.get().setAutoridades(e.getAutoridades());
			eDb.get().setCantVictimas(e.getCantVictimas());
			eDb.get().setTiempoInicio(e.getTiempoInicio());
			eDb.get().setTiempoFin(e.getTiempoFin());
			eDb.get().setTipo(e.getTipo());
			eDb.get().setUbicacionEvento(e.getUbicacionEvento());
			eDb.get().setUbiLatitud(e.getUbiLatitud());
			eDb.get().setUbiLongitud(e.getUbiLongitud());
			repositorio.save(eDb.get());
		}else {
			return 2;
		}
		return 1;
	}
}
