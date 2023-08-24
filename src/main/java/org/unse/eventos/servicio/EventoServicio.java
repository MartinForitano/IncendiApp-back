package org.unse.eventos.servicio;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unse.eventos.entidad.DTOEVentoResponse;
import org.unse.eventos.entidad.DTOListadoGeneral;
import org.unse.eventos.entidad.Evento;
import org.unse.eventos.repositorio.EventosRepositorio;

@Service
public class EventoServicio {

	@Autowired
	private EventosRepositorio repositorio;

	/*
	 * Devuelve una lista con todos los eventos
	 */

	public DTOListadoGeneral listaEventosGral() {
		DTOListadoGeneral respuesta;
		DTOEVentoResponse e = null;
		List<DTOEVentoResponse> listaEventosRespuesta = new ArrayList<>();
		List<Evento> listaGralVanilla = repositorio.findAll();
		for (int i = 0; i < listaGralVanilla.size(); i++) {
			Date TI, TF = null;
			TI =  new Date(listaGralVanilla.get(i).getTiempoInicio().getYear(), listaGralVanilla.get(i).getTiempoInicio().getMonthValue(), listaGralVanilla.get(i).getTiempoInicio().getDayOfMonth(), listaGralVanilla.get(i).getTiempoInicio().getHour(), listaGralVanilla.get(i).getTiempoInicio().getMinute());
			if(listaGralVanilla.get(i).getTiempoFin() != null) {
			TF = new Date(listaGralVanilla.get(i).getTiempoFin().getYear(), listaGralVanilla.get(i).getTiempoFin().getMonthValue(), listaGralVanilla.get(i).getTiempoFin().getDayOfMonth(), listaGralVanilla.get(i).getTiempoFin().getHour(), listaGralVanilla.get(i).getTiempoFin().getMinute());
			e = new DTOEVentoResponse(listaGralVanilla.get(i).getId(), listaGralVanilla.get(i).getTipo() , listaGralVanilla.get(i).getCantVictimas(), listaGralVanilla.get(i).getAutoridades(), listaGralVanilla.get(i).getAreaInfluencia(), listaGralVanilla.get(i).getUbicacionEvento(), TI.getTime(), TF.getTime(), listaGralVanilla.get(i).getUbiLatitud(), listaGralVanilla.get(i).getUbiLongitud(), listaGralVanilla.get(i).getEsVerificado());
			}else {
			e = new DTOEVentoResponse(listaGralVanilla.get(i).getId(), listaGralVanilla.get(i).getTipo() , listaGralVanilla.get(i).getCantVictimas(), listaGralVanilla.get(i).getAutoridades(), listaGralVanilla.get(i).getAreaInfluencia(), listaGralVanilla.get(i).getUbicacionEvento(), TI.getTime(), null, listaGralVanilla.get(i).getUbiLatitud(), listaGralVanilla.get(i).getUbiLongitud(), listaGralVanilla.get(i).getEsVerificado());
			}
			listaEventosRespuesta.add(e);
		}
		respuesta = new DTOListadoGeneral("Ok", listaEventosRespuesta);
		return respuesta;
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
					return buscarPorUbicacionYTipo(ubicacionEvento, tipo);
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

	private List<Evento> buscarPorUbicacionYTipo(String ubicacion, String tipo){
		return buscarPorUbicacionYTipo(ubicacion, tipo);
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

	public Integer altaEvento(DTOEVentoResponse e) {
		if (e.getId() == null) {
			//crear un evento con los datos del DTO y grabar
			LocalDateTime ti, tf = null;
			Date di, df;
			di = new Date(e.getTiempoInicio());
			ti = di.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			if(e.getTiempoFin() != null) {
				df = new Date(e.getTiempoFin());
				tf = Instant.ofEpochMilli(e.getTiempoFin()).atZone(ZoneId.systemDefault()).toLocalDateTime();
			}
			Evento evento = new Evento(null, e.getTipo(), e.getCantVictimas(), e.getAutoridades(), e.getAreaInfluencia(), e.getUbicacionEvento(), ti, tf, e.getUbiLatitud(), e.getUbiLongitud(), e.getEsVerificado());
			repositorio.save(evento);
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

	public Integer cambiarDatosEvento(DTOEVentoResponse e) {
		Date di,df;
	Optional<Evento> eDb = repositorio.findById(e.getId());
		if (eDb.isPresent()) {
			di = new Date(e.getTiempoInicio());
			eDb.get().setAreaInfluencia(e.getAreaInfluencia());
			eDb.get().setAutoridades(e.getAutoridades());
			eDb.get().setCantVictimas(e.getCantVictimas());
			eDb.get().setTiempoInicio(LocalDateTime.of(di.getYear(), di.getMonth(),di.getDate(), di.getHours(), di.getMinutes()));
			if(e.getTiempoFin() != null) {
			df = new Date(e.getTiempoFin());
			eDb.get().setTiempoFin(LocalDateTime.of(df.getYear(), df.getMonth(),df.getDate(), df.getHours(), df.getMinutes()));
			}else {
				eDb.get().setTiempoFin(null);
			}
			//eDb.get().setTiempoInicio(Instant.ofEpochMilli(e.getTiempoInicio()).atZone(ZoneId.systemDefault()).toLocalDateTime());
			//eDb.get().setTiempoFin(Instant.ofEpochMilli(e.getTiempoFin()).atZone(ZoneId.systemDefault()).toLocalDateTime());
			eDb.get().setTipo(e.getTipo());
			eDb.get().setUbicacionEvento(e.getUbicacionEvento());
			eDb.get().setUbiLatitud(e.getUbiLatitud());
			eDb.get().setUbiLongitud(e.getUbiLongitud());
			eDb.get().setEsVerificado(e.getEsVerificado());
			repositorio.save(eDb.get());
			return 1;
		}else {
			return 2;
		}
	}

	public DTOEVentoResponse buscarEvento(Long idEvento) {
		DTOEVentoResponse response;
		Optional<Evento> eDb = repositorio.findById(idEvento);
		if (eDb.isPresent()) {
			if(eDb.get().getTiempoFin()!=null) {
			response = new DTOEVentoResponse(eDb.get().getId(), eDb.get().getTipo(), eDb.get().getCantVictimas(), eDb.get().getAutoridades(), eDb.get().getAreaInfluencia(), eDb.get().getUbicacionEvento(), new Date(eDb.get().getTiempoInicio().getYear(), eDb.get().getTiempoInicio().getMonthValue(), eDb.get().getTiempoInicio().getDayOfMonth(), eDb.get().getTiempoInicio().getHour(), eDb.get().getTiempoInicio().getMinute()).getTime(), new Date(eDb.get().getTiempoFin().getYear(), eDb.get().getTiempoFin().getMonthValue(), eDb.get().getTiempoFin().getDayOfMonth(), eDb.get().getTiempoFin().getHour(), eDb.get().getTiempoFin().getMinute()).getTime(), eDb.get().getUbiLatitud(), eDb.get().getUbiLongitud(), eDb.get().getEsVerificado());
			}else {
				response = new DTOEVentoResponse(eDb.get().getId(), eDb.get().getTipo(), eDb.get().getCantVictimas(), eDb.get().getAutoridades(), eDb.get().getAreaInfluencia(), eDb.get().getUbicacionEvento(), new Date(eDb.get().getTiempoInicio().getYear(), eDb.get().getTiempoInicio().getMonthValue(), eDb.get().getTiempoInicio().getDayOfMonth(), eDb.get().getTiempoInicio().getHour(), eDb.get().getTiempoInicio().getMinute()).getTime(), null, eDb.get().getUbiLatitud(), eDb.get().getUbiLongitud(), eDb.get().getEsVerificado());
			}
			return response;
		}else {
			return null;
		}
	}
	
	public DTOListadoGeneral listadoEventosEnCurso() {
		DTOListadoGeneral respuesta;
		DTOEVentoResponse e;
		List<DTOEVentoResponse> listaEventosRespuesta = new ArrayList<>();
		List<Evento> listaGralVanilla = repositorio.buscarEventosEnCurso();
		for (int i = 0; i < listaGralVanilla.size(); i++) {
			Date TI =  new Date(listaGralVanilla.get(i).getTiempoInicio().getYear(), listaGralVanilla.get(i).getTiempoInicio().getMonthValue(), listaGralVanilla.get(i).getTiempoInicio().getDayOfMonth(), listaGralVanilla.get(i).getTiempoInicio().getHour(), listaGralVanilla.get(i).getTiempoInicio().getMinute());
			e = new DTOEVentoResponse(listaGralVanilla.get(i).getId(), listaGralVanilla.get(i).getTipo() , listaGralVanilla.get(i).getCantVictimas(), listaGralVanilla.get(i).getAutoridades(), listaGralVanilla.get(i).getAreaInfluencia(), listaGralVanilla.get(i).getUbicacionEvento(), TI.getTime(), null, listaGralVanilla.get(i).getUbiLatitud(), listaGralVanilla.get(i).getUbiLongitud(), listaGralVanilla.get(i).getEsVerificado());
			listaEventosRespuesta.add(e);
		}
		respuesta = new DTOListadoGeneral("Ok", listaEventosRespuesta);
		return respuesta;
	}
	
	public DTOListadoGeneral listadoEventosVerificados() {
		DTOListadoGeneral respuesta;
		DTOEVentoResponse e;
		List<DTOEVentoResponse> listaEventosRespuesta = new ArrayList<>();
		List<Evento> listaGralVanilla = repositorio.buscarEventosVerificados();
		for (int i = 0; i < listaGralVanilla.size(); i++) {
			Date TI =  new Date(listaGralVanilla.get(i).getTiempoInicio().getYear(), listaGralVanilla.get(i).getTiempoInicio().getMonthValue(), listaGralVanilla.get(i).getTiempoInicio().getDayOfMonth(), listaGralVanilla.get(i).getTiempoInicio().getHour(), listaGralVanilla.get(i).getTiempoInicio().getMinute());
			e = new DTOEVentoResponse(listaGralVanilla.get(i).getId(), listaGralVanilla.get(i).getTipo() , listaGralVanilla.get(i).getCantVictimas(), listaGralVanilla.get(i).getAutoridades(), listaGralVanilla.get(i).getAreaInfluencia(), listaGralVanilla.get(i).getUbicacionEvento(), TI.getTime(), null, listaGralVanilla.get(i).getUbiLatitud(), listaGralVanilla.get(i).getUbiLongitud(), listaGralVanilla.get(i).getEsVerificado());
			listaEventosRespuesta.add(e);
		}

		respuesta = new DTOListadoGeneral("Ok", listaEventosRespuesta);
		return respuesta;
	}
	
	public DTOListadoGeneral listadoEventosEnCursoSinVerificar() {
		DTOListadoGeneral respuesta;
		DTOEVentoResponse e;
		List<DTOEVentoResponse> listaEventosRespuesta = new ArrayList<>();
		List<Evento> listaGralVanilla = repositorio.buscarEventosEnCursoSinVerificar();
		for (int i = 0; i < listaGralVanilla.size(); i++) {
			Date TI =  new Date(listaGralVanilla.get(i).getTiempoInicio().getYear(), listaGralVanilla.get(i).getTiempoInicio().getMonthValue(), listaGralVanilla.get(i).getTiempoInicio().getDayOfMonth(), listaGralVanilla.get(i).getTiempoInicio().getHour(), listaGralVanilla.get(i).getTiempoInicio().getMinute());
			e = new DTOEVentoResponse(listaGralVanilla.get(i).getId(), listaGralVanilla.get(i).getTipo() , listaGralVanilla.get(i).getCantVictimas(), listaGralVanilla.get(i).getAutoridades(), listaGralVanilla.get(i).getAreaInfluencia(), listaGralVanilla.get(i).getUbicacionEvento(), TI.getTime(), null, listaGralVanilla.get(i).getUbiLatitud(), listaGralVanilla.get(i).getUbiLongitud(), listaGralVanilla.get(i).getEsVerificado());
			listaEventosRespuesta.add(e);
		}
		respuesta = new DTOListadoGeneral("Ok", listaEventosRespuesta);
		return respuesta;
	}
}
