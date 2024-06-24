package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.Reserva.Reserva;
import FYGuide2.FYGuide2.repository.ServicioRepository;
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

import java.util.*;

@Service
public class GuiaService {

    private final GuiaRepository guiaRepository;

    private final TuristaRepository turistaRepository;

    private final ServicioRepository servicioRepository;


    @Autowired
    public GuiaService(GuiaRepository guiaRepository, TuristaRepository turistaRepository, ServicioRepository servicioRepository) {
        this.guiaRepository = guiaRepository;
        this.turistaRepository = turistaRepository;
        this.servicioRepository = servicioRepository;
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

    public Optional<Guia> addServiceToGuia(Long guiaId, Servicio servicio) {
        Optional<Guia> guiaOptional = guiaRepository.findById(guiaId);
        if (guiaOptional.isPresent()) {
            Guia guia = guiaOptional.get();
            guia.getServiciosOfrecidos().add(servicio);
            guiaRepository.save(guia);
            return guiaOptional;
        }

        return null; // or throw exception if guiaId is not found
    }

    public Optional<Guia> removeServiceFromGuia(Long guiaId, Long servicioId) {
        Optional<Guia> guiaOptional = guiaRepository.findById(guiaId);

        if (guiaOptional.isPresent()) {
            Guia guia = guiaOptional.get();
            guia.getServiciosOfrecidos().removeIf(s -> s.getId().equals(servicioId));
            guiaRepository.save(guia);
            return guiaOptional;
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


    public boolean isGuiaAvaible(Long idServicio, Date fechaInicio, String destino) {
        Servicio servicio = servicioRepository.findById(idServicio).orElse(null);

        Long guiaId = servicio.getGuiaId();
        Guia guia = guiaRepository.findById(guiaId).orElse(null);


        List<Reserva> reservas = guia.getReservas();
        List<String> locations = guia.getLocations();

        if (!locations.contains(destino)) {
            return false;
        }

        Calendar now = Calendar.getInstance();
        Calendar nextWeek = Calendar.getInstance();
        nextWeek.add(Calendar.WEEK_OF_YEAR, 1);

        Calendar fechaInicioCal = Calendar.getInstance();
        fechaInicioCal.setTime(fechaInicio);
        fechaInicioCal.set(Calendar.HOUR_OF_DAY, 0);
        fechaInicioCal.set(Calendar.MINUTE, 0);
        fechaInicioCal.set(Calendar.SECOND, 0);
        fechaInicioCal.set(Calendar.MILLISECOND, 0);
        fechaInicio = fechaInicioCal.getTime();

        if (fechaInicio.before(now.getTime()) || fechaInicio.before(nextWeek.getTime())) {
            return false;
        }

        for (Reserva reserva : reservas) {

            Calendar reservaFechaInicioCal = Calendar.getInstance();
            reservaFechaInicioCal.setTime(reserva.getFechaInicio());
            reservaFechaInicioCal.set(Calendar.HOUR_OF_DAY, 0);
            reservaFechaInicioCal.set(Calendar.MINUTE, 0);
            reservaFechaInicioCal.set(Calendar.SECOND, 0);
            reservaFechaInicioCal.set(Calendar.MILLISECOND, 0);
            Date reservaFechaInicio = reservaFechaInicioCal.getTime();

            if (reservaFechaInicio.equals(fechaInicio)) {
                return false;
            }
        }
        return true;
    }





}






