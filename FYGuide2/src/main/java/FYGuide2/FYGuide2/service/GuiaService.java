package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.repository.GuiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuiaService {

    private final GuiaRepository guiaRepository;

    @Autowired
    public GuiaService(GuiaRepository guiaRepository) {
        this.guiaRepository = guiaRepository;
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
}
