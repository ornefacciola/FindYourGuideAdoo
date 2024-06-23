package FYGuide2.FYGuide2.rest;


import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;
import FYGuide2.FYGuide2.model.Reserva.Reserva;
import FYGuide2.FYGuide2.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservas")
public class ReservaController {
    //private final ServicioService servicioService;
    private final ReservaService reservaService;
    private final Notificador notificador;


    public ReservaController(ReservaService reservaService, Notificador notificador) {
        this.reservaService = reservaService;
        this.notificador = notificador;
    }

    @PutMapping("/{idReserva}/aceptar")
    public ResponseEntity<Notificacion> aceptarReserva(@PathVariable Long idReserva) {
        Reserva reserva = reservaService.getReservaById(idReserva);

        if (reserva != null) {
            Notificacion noti = reservaService.aceptarReserva(reserva);
            return new ResponseEntity<>(noti, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idReserva}/rechazar")
    public ResponseEntity<Notificacion> rechazarReserva(@PathVariable Long idReserva) {
        Reserva reserva = reservaService.getReservaById(idReserva);

        if (reserva != null) {
            Notificacion noti = reservaService.rechazarReserva(reserva);
            return new ResponseEntity<>(noti, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{idReserva}/cancelar")
    public ResponseEntity<Notificacion> cancelarReserva(@PathVariable Long idReserva) {
        Reserva reserva = reservaService.getReservaById(idReserva);

        if (reserva != null) {
            Notificacion noti = reservaService.cancelarReserva(reserva);
            return new ResponseEntity<>(noti, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{idReserva}/finalizar")
    public ResponseEntity<Notificacion> finalizarReserva(@PathVariable Long idReserva) {
        Reserva reserva = reservaService.getReservaById(idReserva);

        if (reserva != null) {
            Notificacion noti = reservaService.finalizarReserva(reserva);
            return new ResponseEntity<>(noti, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
