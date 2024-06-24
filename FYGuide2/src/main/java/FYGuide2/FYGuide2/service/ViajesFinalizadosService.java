package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.ViajesFinalizados;
import FYGuide2.FYGuide2.repository.ViajesFinalizadosRepository;
import org.springframework.stereotype.Service;

@Service
public class ViajesFinalizadosService {

    private final ViajesFinalizadosRepository viajesFinalizadosRepository;

    public ViajesFinalizadosService(ViajesFinalizadosRepository viajesFinalizadosRepository) {
        this.viajesFinalizadosRepository = viajesFinalizadosRepository;
    }

    public Iterable<ViajesFinalizados> getAllViajesFinalizadosGuia(Long guiaId) {
        return viajesFinalizadosRepository.findByGuiaId(guiaId);
    }

    public Iterable<ViajesFinalizados> getAllViajesFinalizadosTurista(Long turistaId) {
        return viajesFinalizadosRepository.findByTuristaId(turistaId);
    }
}
