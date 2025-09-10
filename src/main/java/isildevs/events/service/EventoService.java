package isildevs.events.service;
import isildevs.events.dto.AsistenciaRequest;
import isildevs.events.dto.EventoRequest;
import isildevs.events.model.Evento;
import isildevs.events.model.Asistencia;
import isildevs.events.repository.AsistenciaRepository;
import isildevs.events.exception.ResourceNotFoundException;
import isildevs.events.repository.EventoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional


public class EventoService {
    private final EventoRepository eventoRepo;
    private final AsistenciaRepository asistenciaRepo;

    public EventoService(EventoRepository eventoRepo, AsistenciaRepository asistenciaRepo) {
        this.eventoRepo = eventoRepo;
        this.asistenciaRepo = asistenciaRepo;


    }
    public Evento crearEvento(EventoRequest req){
        Evento e = new Evento();
        e.setFecha(req.getFecha());
        e.setNombre(req.getNombre());
        e.setLugar(req.getLugar());
        e.setPonente(req.getPonente());
        e.setOrganizador(req.getOrganizador());
        e.setLink(req.getLink());
        e.setImagen(req.getImagenUrl());
        e.setDescripcion(req.getDescripcion());
        return eventoRepo.save(e);
    }
    public List<Evento> listarEventos(){
        return eventoRepo.findAll();
    }
    public Evento obtenerEvento(Long id){
        return eventoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));
    }
    public Evento actualizarEvento(Long id, EventoRequest req){
        Evento e= obtenerEvento(id);
        e.setFecha(req.getFecha());
        e.setNombre(req.getNombre());
        e.setLugar(req.getLugar());
        e.setPonente(req.getPonente());
        e.setOrganizador(req.getOrganizador());
        e.setLink(req.getLink());
        e.setImagen(req.getImagenUrl());
        e.setDescripcion(req.getDescripcion());
        return eventoRepo.save(e);
    }

    public void eliminarEvento(Long id) {
        Evento e = obtenerEvento(id);
        eventoRepo.delete(e);
    }

    public Asistencia registrarAsistencia(Long eventoId, AsistenciaRequest req) {
        Evento e = obtenerEvento(eventoId);
        Asistencia a = new Asistencia();
        a.setNombre(req.getNombre());
        a.setCorreo(req.getCorreo());
        a.setTelefono(req.getTelefono());
        a.setEvento(e);
        return asistenciaRepo.save(a);
    }

    public List<Asistencia> listarAsistencias(Long eventoId) {
        return asistenciaRepo.findByEvento_Id(eventoId);
    }
}
