package FYGuide2.FYGuide2.service;


import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Reserva.Reserva;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.model.ViajesFinalizados;
import FYGuide2.FYGuide2.repository.ReservaRepository;
import FYGuide2.FYGuide2.repository.ViajesFinalizadosRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ViajesFinalizadosRepository viajesFinalizadosRepository;
    public ReservaService(ReservaRepository reservaRepository,  ViajesFinalizadosRepository viajesFinalizadosRepository) {
        this.reservaRepository = reservaRepository;
        this.viajesFinalizadosRepository = viajesFinalizadosRepository;
    }

    public Iterable<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Reserva getReservaById(Long idReserva) {
        return reservaRepository.findById(idReserva).orElse(null);
    }

    public Notificacion addReserva(Servicio servicio, Date fechaInicio, String destino, Long turista) {
        Reserva reserva = new Reserva(servicio, fechaInicio, destino, turista,servicio.getPrecio() * 0.25);
        Notificacion notificacion = new Notificacion("Reserva creada", new Date(), turista);
        reservaRepository.save(reserva);
        return notificacion;
    }

    public Notificacion aceptarReserva(Reserva reserva) {
        Notificacion notificacion = reserva.aceptar();
        reservaRepository.save(reserva);
        return notificacion;
    }

    public Notificacion rechazarReserva(Reserva reserva) {
        Notificacion notificacion = reserva.rechazar();
        reservaRepository.save(reserva);
        return notificacion;
    }

    public Notificacion cancelarReserva(Reserva reserva) {
        Notificacion notificacion = reserva.cancelar();
        reservaRepository.save(reserva);
        return notificacion;
    }


    public Notificacion finalizarReserva(Reserva reserva) {

        ViajesFinalizados viaje = new ViajesFinalizados(
                reserva.getFechaInicio(),
                reserva.getTuristaId(),
                reserva.getGuiaId(),
                reserva.getServicio().getId(),
                reserva.getDestino()
        );

        viajesFinalizadosRepository.save(viaje);
        Notificacion notificacion = reserva.finalizar();
        reservaRepository.save(reserva);
        return notificacion;
    }

}
