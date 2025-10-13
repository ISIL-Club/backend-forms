package isildevs.events.controller;
// AJA
import isildevs.events.dto.EventoRequest;
import isildevs.events.dto.AsistenciaRequest;
import isildevs.events.model.Asistencia;
import isildevs.events.model.Evento;
import isildevs.events.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 📌 Swagger imports
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "*") // ajustar en producción
@Tag(name = "Eventos", description = "Gestión de eventos y asistencias")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @Operation(summary = "ESTO USA EN EL FRONTEND PARA LLAMAR LOS EVENTOS Listar todos los eventos", description = "Devuelve una lista de eventos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido con éxito")
    })
    @GetMapping
    public List<Evento> getAll() {
        return service.listarEventos();
    }

    @Operation(summary = "Obtener un evento por ID esto no se usara tanto", description = "Busca un evento específico por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @GetMapping("/{id}")
    public Evento getOne(@PathVariable Long id) {
        return service.obtenerEvento(id);
    }

    @Operation(summary = "Crear un nuevo evento", description = "Registra un evento en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evento create(@Valid @RequestBody EventoRequest eventoReq) {
        return service.crearEvento(eventoReq);
    }

    @Operation(summary = "Actualizar un evento", description = "Modifica los datos de un evento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @PutMapping("/{id}")
    public Evento update(@PathVariable Long id, @Valid @RequestBody EventoRequest eventoReq) {
        return service.actualizarEvento(id, eventoReq);
    }

    @Operation(summary = "Eliminar un evento", description = "Borra un evento de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.eliminarEvento(id);
    }

    /* --- Endpoints para asistencias --- */

    @Operation(summary = "Registrar asistencia a un evento", description = "Permite que un usuario confirme su asistencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Asistencia registrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @PostMapping("/{id}/attend")
    @ResponseStatus(HttpStatus.CREATED)
    public Asistencia attend(@PathVariable("id") Long eventoId,
                             @Valid @RequestBody AsistenciaRequest req) {
        return service.registrarAsistencia(eventoId, req);
    }

    @Operation(summary = "Listar asistencias de un evento", description = "Devuelve la lista de asistentes a un evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de asistencias obtenido con éxito"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @GetMapping("/{id}/attendances")
    public ResponseEntity<List<Asistencia>> listAttendances(@PathVariable("id") Long eventoId) {
        return ResponseEntity.ok(service.listarAsistencias(eventoId));
    }
}
