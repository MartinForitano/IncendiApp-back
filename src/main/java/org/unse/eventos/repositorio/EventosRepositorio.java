package org.unse.eventos.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.unse.eventos.entidad.Evento;


@Repository
public interface EventosRepositorio extends JpaRepository<Evento, Long>{
	
	
	@Query("select e from evento e where e.tipo = ?1")
	List<Evento> buscarPorTipoSinFecha(String tipo);
	  
	@Query("select e from evento e where e.zona = ?1")
	List<Evento> buscarPorZonaSinFecha(String zona);
	
	@Query("select e from evento e where e.zona = ?1 and e.tiempoinicio >= ?2 and e.tiempofin <= ?3")
	List<Evento> buscarPorZonaConFecha(String zona, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	@Query("select e from evento e where e.tipo = ?1 and e.tiempoinicio >= ?2 and e.tiempofin <= ?3")
	List<Evento> buscarPorTipoConFecha(String tipo, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	@Query("select e from evento e where e.tiempoinicio >= ?1 and e.tiempofin <= ?2")
	List<Evento> buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
