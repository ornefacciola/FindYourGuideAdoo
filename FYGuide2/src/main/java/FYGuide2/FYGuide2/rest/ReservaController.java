package FYGuide2.FYGuide2.rest;


import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;
import FYGuide2.FYGuide2.model.Reserva.Reserva;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.service.GuiaService;
import FYGuide2.FYGuide2.service.ReservaService;
import FYGuide2.FYGuide2.service.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("reservas")
public class ReservaController {

    private final GuiaService guiaService;
    private final ServicioService servicioService;
    private final ReservaService reservaService;
    private final Notificador notificador;


    public ReservaController(ReservaService reservaService, ServicioService servicioService, GuiaService guiaService, Notificador notificador) {
        this.reservaService = reservaService;
        this.servicioService = servicioService;
        this.guiaService = guiaService;
        this.notificador = notificador;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Reserva>> getAllReservas() {
        Iterable<Reserva> reservas = reservaService.getAllReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @GetMapping("/{idReserva}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long idReserva) {
        System.out.println("GET RESERVA: " + idReserva);
        Reserva reserva = reservaService.getReservaById(idReserva);

        if (reserva != null) {
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
            System.out.println("Hola");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{idServicio}/contratar/{idTurista}")
    public ResponseEntity<Notificacion> contratarServicio(
            @PathVariable Long idServicio,
            @PathVariable Long idTurista,
            @RequestParam Date fechaInicio,
            @RequestParam String destino
    ) {
        boolean isAvaible = guiaService.isGuiaAvaible(idServicio, fechaInicio, destino);

        if (isAvaible) {
            Servicio servicio = servicioService.getServiceById(idServicio);
            Notificacion noti = reservaService.addReserva(servicio, fechaInicio, destino, idTurista);
            return new ResponseEntity<>(noti, HttpStatus.CREATED);
        } else {
            Notificacion noti= new Notificacion("No puedes contratar este servicio", new Date(), null);
            return new ResponseEntity<>(noti, HttpStatus.BAD_REQUEST);
        }
    }


}
