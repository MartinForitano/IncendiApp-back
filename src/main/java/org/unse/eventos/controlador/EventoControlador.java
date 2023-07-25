package org.unse.eventos.controlador;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unse.eventos.entidad.DTOEVentoResponse;
import org.unse.eventos.entidad.DTOListadoGeneral;
import org.unse.eventos.entidad.Evento;
import org.unse.eventos.servicio.EventoServicio;
import org.unse.usuarios.entidad.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class EventoControlador {

	@Autowired
	private EventoServicio servicio;

	public EventoControlador() {
	}

	@Operation(summary = "Listado general", description = "Listado general de eventos", tags = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se encontraron eventos") })
	@GetMapping(produces = "application/json", path = "/eventos/listado/general/")
	public ResponseEntity<DTOListadoGeneral> listaEventos() {
		return ResponseEntity.ok(servicio.listaEventosGral());
	}

	@Operation(summary = "Listado con filtro", description = "Listado de eventos con filtro", tags = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se encontraron eventos") })
	@GetMapping(produces = "application/json", path = "/eventos/listado/filtrado")
	public ResponseEntity<List<Evento>> listaEventosFiltrado(
			@Parameter(description = "Ubicacion del evento") @PathVariable String ubiEvento,
			@Parameter(description = "Tipo de evento") @PathVariable String tipo,
			@Parameter(description = "ID de la laptop a buscar") @PathVariable LocalDateTime fechaIni,
			@Parameter(description = "ID de la laptop a buscar") @PathVariable LocalDateTime fechaFin) {
		return ResponseEntity.ok(servicio.listaEventoFiltrada(ubiEvento, tipo, fechaIni, fechaFin));
	}

	@Operation(summary = "Alta de evento", description = "Alta de los datos de un evento", tags = "POST")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Evento cargado"),
			@ApiResponse(responseCode = "400", description = "No se debe cargar un evento con id") })
	@PostMapping(produces = "application/json", consumes = "application/json", path = "/eventos/alta/")
	public ResponseEntity<DTOEVentoResponse> altaEvento(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del evento a registrar") @RequestBody DTOEVentoResponse e) {
		switch (servicio.altaEvento(e)) {
		case 1:
			return ResponseEntity.ok().build();
		case 2:
			return ResponseEntity.badRequest().build();
		default:
			return ResponseEntity.internalServerError().build();
		}
	}

	@Operation(summary = "Baja de evento", description = "Borrar datos de un evento usando su ID", tags = "DELETE")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Evento borrado"),
			@ApiResponse(responseCode = "404", description = "Evento no existente") })
	@DeleteMapping("/eventos/baja/{id}")
	public ResponseEntity<Evento> bajaEvento(@Parameter(description = "ID del evento a borrar") @PathVariable Long id) {
		if (servicio.borrarEvento(id) == 1) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Cambio de datos de evento", description = "Cambio en los datos de un evento especifico", tags = "PUT")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Datos actualizados"),
			@ApiResponse(responseCode = "404", description = "Evento no encontrado")})
	@PutMapping(produces = "application/json", consumes = "application/json", path = "/eventos/actualizacion/")
	public ResponseEntity<Usuario> cambiarContraseniaUsuario(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del evento para actualizar") @RequestBody Evento e) {
		switch (servicio.cambiarDatosEvento(e)) {
		case 1:
			return ResponseEntity.ok().build();
		case 2:
			return ResponseEntity.notFound().build();
		default:
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@Operation(summary = "Recuperar evento", description = "Recuperar un evento con id", tags = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se encontraron eventos") })
	@GetMapping(produces = "application/json", path = "/eventos/{id}/")
	public ResponseEntity<DTOEVentoResponse> recuperarEvento(
			@Parameter(description = "Id del evento") @PathVariable Long id) {
		return ResponseEntity.ok(servicio.buscarEvento(id));
	}
	
	
	
}
