package FYGuide2.FYGuide2.repository;

import FYGuide2.FYGuide2.model.ViajesFinalizados;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViajesFinalizadosRepository extends JpaRepository<ViajesFinalizados, Long> {
    List<ViajesFinalizados> findByTuristaId(Long turistaId);
    List<ViajesFinalizados> findByGuiaId(Long guiaId);
}
