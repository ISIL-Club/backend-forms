package isildevs.events.repository;
import isildevs.events.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia,Long> {

    //encontrar por id

    List<Asistencia> findByEventoId(Long eventoId);

    List<Asistencia> findByEvento_Id(Long eventoId);
}
