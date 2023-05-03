package org.unse.eventos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unse.eventos.entidad.Evento;


@Repository
public interface EventosRepositorio extends JpaRepository<Evento, Long>{

}
