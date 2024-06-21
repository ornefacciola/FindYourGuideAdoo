package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.repository.GuiaRepository;
import FYGuide2.FYGuide2.repository.TuristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TuristaService {
    private final TuristaRepository turistaRepository;

    @Autowired
    public TuristaService(TuristaRepository turistaRepository) {
        this.turistaRepository = turistaRepository;
    }

    public Turista addTurista(Turista turista) {
        // Assuming logic for validation or additional processing before saving
        return turistaRepository.save(turista);
    }

    public Turista getTuristaById(Long turistaId) {
        return turistaRepository.findById(turistaId).orElse(null);
    }

    public Iterable<Turista> getAllTuristas() {
        return turistaRepository.findAll();
    }

    public void deleteTurista(Long turistaId) {
        turistaRepository.deleteById(turistaId);
    }

}
