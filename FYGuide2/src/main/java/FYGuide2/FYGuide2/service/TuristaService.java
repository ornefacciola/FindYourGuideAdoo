package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.Factura.Factura;
import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.model.User;
import FYGuide2.FYGuide2.repository.FacturaRepository;
import FYGuide2.FYGuide2.repository.GuiaRepository;
import FYGuide2.FYGuide2.repository.TuristaRepository;
import FYGuide2.FYGuide2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

@Service
public class TuristaService {


    private FacturaRepository facturaRepository;
    private final TuristaRepository turistaRepository;
    private final GuiaRepository guiaRepository;
    private final UserRepository userRepository;


    public TuristaService(TuristaRepository turistaRepository, GuiaRepository guiaRepository, UserRepository userRepository, FacturaRepository facturaRepository) {
        this.turistaRepository = turistaRepository;
        this.guiaRepository = guiaRepository;
        this.userRepository = userRepository;
        this.facturaRepository = facturaRepository;
    }

    public Turista addTurista(Turista turista) {
        return turistaRepository.save(turista);
    }

    public Turista getTuristaById(Long turistaId) {
        return (Turista)(userRepository.findById(turistaId).orElse(null));
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

            Guia guiaToCreate = new Guia(
                    turista.getEmail(),
                    turista.getUserId(),
                    turista.getUserPassword(),
                    turista.getUsername(),
                    turista.getSex(),
                    turista.getFirstName(),
                    turista.getLastName(),
                    turista.getDni(),
                    turista.getCelular(),
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


    public List<Factura> getFacturas(Long turista) {
        return facturaRepository.findByTurista(turista);
    }

}
