package FYGuide2.FYGuide2.service;


import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Reserva.Reserva;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Notificacion addReserva(Servicio servicio, Date fechaInicio) {
        Reserva reserva = new Reserva(servicio, fechaInicio, servicio.getPrecio() * 0.1);
        Notificacion notificacion = new Notificacion("Reserva creada", new Date(), reserva.getTurista());
        reservaRepository.save(reserva);
        return notificacion;
    }

}
