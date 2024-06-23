package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.rest.DTO.GuiaDTO;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.repository.GuiaRepository;
import FYGuide2.FYGuide2.repository.TuristaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GuiaService {

    private final GuiaRepository guiaRepository;

    private final TuristaRepository turistaRepository;


    @Autowired
    public GuiaService(GuiaRepository guiaRepository, TuristaRepository turistaRepository) {
        this.guiaRepository = guiaRepository;
        this.turistaRepository = turistaRepository;

    }


    public Guia addGuia(Guia guia) {
        // Assuming logic for validation or additional processing before saving
        return guiaRepository.save(guia);
    }

    public Guia getGuiaById(Long guiaId) {
        return guiaRepository.findById(guiaId).orElse(null);
    }

    public Iterable<Guia> getAllGuias() {
        return guiaRepository.findAll();
    }

    public void deleteGuia(Long guiaId) {
        guiaRepository.deleteById(guiaId);
    }

    public Guia addServiceToGuia(Long guiaId, Servicio servicio) {
        Optional<Guia> guiaOptional = guiaRepository.findById(guiaId);

        if (guiaOptional.isPresent()) {
            Guia guia = guiaOptional.get();
            guia.getServiciosOfrecidos().add(servicio);
            guiaRepository.save(guia);
            return guia;
        }

        return null; // or throw exception if guiaId is not found
    }

    public Guia removeServiceFromGuia(Long guiaId, Long servicioId) {
        Optional<Guia> guiaOptional = guiaRepository.findById(guiaId);

        if (guiaOptional.isPresent()) {
            Guia guia = guiaOptional.get();
            guia.getServiciosOfrecidos().removeIf(s -> s.getId().equals(servicioId));
            guiaRepository.save(guia);
            return guia;
        }

        return null; // or throw exception if guiaId is not found
    }

    public ResponseEntity<Void> ChangeProfile(Long userId) {

        Optional<Guia> guiaAborrar = guiaRepository.findById(userId);
        if (guiaAborrar.isPresent()) {
            Guia guia = guiaAborrar.get();

            // Crear turista a partir de guia
            Turista turistaAcrear = new Turista(
                    guia.getUserId(),
                    guia.getEmail(),
                    guia.getUsername(),
                    guia.getUserPassword(),
                    guia.getFirstName(),
                    guia.getLastName(),
                    guia.getDni(),
                    guia.getCelular(),
                    guia.getSex(),
                    guia.getProfilePic()
            );

            // Guardar el turista en el repositorio de Turista
            turistaRepository.save(turistaAcrear);

            // Eliminar el guia
            guiaRepository.deleteById(guiaAborrar.get().getUserId());
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
         else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    public List<Guia> searchGuias(String firstName, String lastName, String location) {
        Specification<Guia> spec = Specification
                .where(GuiaDTO.hasFirstName(firstName))
                .and(GuiaDTO.hasLastName(lastName))
                .and(GuiaDTO.hasCity(location));

        return guiaRepository.findAll(spec);
    }

}






