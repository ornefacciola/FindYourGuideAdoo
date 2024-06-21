package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.repository.GuiaRepository;
import FYGuide2.FYGuide2.repository.TuristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Collections;

@Service
public class TuristaService {
    private final TuristaRepository turistaRepository;
    private final GuiaRepository guiaRepository;

    @Autowired
    public TuristaService(TuristaRepository turistaRepository, GuiaRepository guiaRepository) {
        this.turistaRepository = turistaRepository;
        this.guiaRepository = guiaRepository;
    }

    public Turista addTurista(Turista turista) {
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

    public ResponseEntity<Void> changeProfileToGuia(Long userId) {
        Optional<Turista> turistaOptional = turistaRepository.findById(userId);
        if (turistaOptional.isPresent()) {
            Turista turista = turistaOptional.get();

            // Create Guia from Turista
            Guia guiaToCreate = new Guia(
                    turista.getUserId(),
                    turista.getEmail(),
                    turista.getUsername(),
                    turista.getUserPassword(),
                    turista.getFirstName(),
                    turista.getLastName(),
                    turista.getDni(),
                    turista.getCelular(),
                    turista.getSex(),
                    turista.getProfilePic(),
                    "", // licencia, you can set default or fetch from another source
                    Collections.emptyList() // locations, you can set default or fetch from another source
            );

            // Save Guia in guiaRepository
            guiaRepository.save(guiaToCreate);

            // Delete Turista from turistaRepository
            turistaRepository.deleteById(turista.getUserId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
