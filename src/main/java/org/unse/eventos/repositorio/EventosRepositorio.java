package org.unse.eventos.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.unse.eventos.entidad.Evento;

@Repository
public interface EventosRepositorio extends JpaRepository<Evento, Long> {

	@Query("select e from Evento e where e.tipo = ?1")
	List<Evento> buscarPorTipoSinFecha(String tipo);

	@Query("select e from Evento e where e.ubicacionEvento like %?1%")
	List<Evento> buscarPorUbicacionSinFecha(String ubicacionEvento);

	@Query("select e from Evento e where e.ubicacionEvento like %?1% and e.tiempoInicio >= ?2 and e.tiempoFin <= ?3")
	List<Evento> buscarPorUbicacionConFecha(String ubicacionEvento, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	@Query("select e from Evento e where e.tipo = ?1 and e.tiempoInicio >= ?2 and e.tiempoFin <= ?3")
	List<Evento> buscarPorTipoConFecha(String tipo, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	@Query("select e from Evento e where e.tiempoInicio >= ?1 and e.tiempoFin <= ?2")
	List<Evento> buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

	@Query("select e from Evento e where e.ubicacionEvento like %?1% and e.tipo = ?2")
	List<Evento> buscarPorUbicacionYTipo(String ubicacion, String tipo);

	@Query("select e from Evento e where e.tiempoFin = null and e.esVerificado = true")
	List<Evento> buscarEventosEnCurso();
	
	@Query("select e from Evento e where esVerificado = true")
	List<Evento> buscarEventosVerificados();
	
	@Query("select e from Evento e where e.tiempoFin = null and e.esVerificado = false")
	List<Evento> buscarEventosEnCursoSinVerificar();
	
	@Query("select e from Evento e where e.tiempoFin != null")
	List<Evento> buscarEventosFinalizados();
	
	@Query("select e from Evento e where e.ubicacionEvento like %?1%")
	List<Evento> buscarEventosPorUbicacion(String ubicacion);
	
}
