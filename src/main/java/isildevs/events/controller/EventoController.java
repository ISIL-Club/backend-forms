package isildevs.events.controller;

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

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "*") // ajustar en producción
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Evento> getAll() {
        return service.listarEventos();
    }

    @GetMapping("/{id}")
    public Evento getOne(@PathVariable Long id) {
        return service.obtenerEvento(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evento create(@Valid @RequestBody EventoRequest eventoReq) {
        return service.crearEvento(eventoReq);
    }

    @PutMapping("/{id}")
    public Evento update(@PathVariable Long id, @Valid @RequestBody EventoRequest eventoReq) {
        return service.actualizarEvento(id, eventoReq);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.eliminarEvento(id);
    }

    /* --- Endpoints para asistencias --- */


    @PostMapping("/{id}/attend")
    @ResponseStatus(HttpStatus.CREATED)
    public Asistencia attend(@PathVariable("id") Long eventoId,
                             @Valid @RequestBody AsistenciaRequest req) {
        return service.registrarAsistencia(eventoId, req);
    }


    @GetMapping("/{id}/attendances")
    public ResponseEntity< List<Asistencia> > listAttendances(@PathVariable("id") Long eventoId) {
        return ResponseEntity.ok( service.listarAsistencias(eventoId));
    }
}
