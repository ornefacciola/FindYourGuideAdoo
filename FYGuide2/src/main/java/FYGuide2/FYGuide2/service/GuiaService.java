package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.Reserva.Reserva;
import FYGuide2.FYGuide2.model.Reseña;
import FYGuide2.FYGuide2.repository.ReseñaRepository;
import FYGuide2.FYGuide2.repository.ServicioRepository;
import FYGuide2.FYGuide2.rest.DTO.GuiaDTO;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.repository.GuiaRepository;
import FYGuide2.FYGuide2.repository.TuristaRepository;
import FYGuide2.FYGuide2.rest.DTO.ReseñaDTO;
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

    private final ReseñaRepository reseñaRepository;


    @Autowired
    public GuiaService(GuiaRepository guiaRepository, TuristaRepository turistaRepository, ServicioRepository servicioRepository,  ReseñaRepository reseñaRepository) {
        this.guiaRepository = guiaRepository;
        this.turistaRepository = turistaRepository;
        this.servicioRepository = servicioRepository;
        this.reseñaRepository = reseñaRepository;
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

    public ResponseEntity<Void> changeProfile(Long userId) {

        Optional<Guia> guiaAborrar = guiaRepository.findById(userId);
        if (guiaAborrar.isPresent()) {
            Guia guia = guiaAborrar.get();


            // Crear turista a partir de guia
            Turista turistaAcrear = new Turista(
                    guia.getEmail(),
                    guia.getUserId(),
                    guia.getUserPassword(),
                    guia.getUsername(),
                    guia.getSex(),
                    guia.getFirstName(),
                    guia.getLastName(),
                    guia.getDni(),
                    guia.getCelular(),
                    guia.getProfilePic()
            );

            turistaRepository.save(turistaAcrear);

            guiaRepository.deleteById(guiaAborrar.get().getUserId());
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    public List<Guia> searchGuias(String firstName, String lastName, String location, Double rating){
        Specification<Guia> spec = Specification
                .where(GuiaDTO.hasFirstName(firstName))
                .and(GuiaDTO.hasLastName(lastName))
                .and(GuiaDTO.hasCity(location))
                .and(GuiaDTO.rating(rating));

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

            if (reservaFechaInicio.equals(fechaInicio) && (
                            Objects.equals(reserva.getEstado(), "Aceptado") ||
                            Objects.equals(reserva.getEstado(), "Pendiente"))){
                return false;
            };
        }
        return true;
    };

    public String addReseña(Long guiaId, Long idTurista, ReseñaDTO reseñaRequest) {
        Guia guia = guiaRepository.findById(guiaId).orElse(null);
        Turista turista = turistaRepository.findById(idTurista).orElse(null);

        String comentario = reseñaRequest.getComentario();
        Integer puntuacion = reseñaRequest.getPuntuacion();


        Reseña reseña = new Reseña(turista.getUserId(), guia.getUserId(), comentario, puntuacion);
        reseñaRepository.save(reseña);




        guia.setCantReseñas(guia.getCantReseñas() + 1);
        guia.setPuntuacion(guia.getPuntuacion() + (double)puntuacion);
        guia.setPuntuacionTotal(guia.getPuntuacion() / (double)guia.getCantReseñas());
        turista.setCantReseñas(turista.getCantReseñas() + 1);



        guia.notificarTrofeo(2);
        String a = turista.notificarTrofeo(1);



        guiaRepository.save(guia);
        turistaRepository.save(turista);

        return a;

    }


}






