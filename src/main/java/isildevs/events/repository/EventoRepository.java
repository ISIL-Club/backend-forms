package isildevs.events.repository;
import  isildevs.events.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends  JpaRepository<Evento, Long>{

}
