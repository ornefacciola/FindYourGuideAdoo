package FYGuide2.FYGuide2.rest;


import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.service.ReservaService;
import FYGuide2.FYGuide2.service.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final ReservaService reservaService;

    private final Notificador notificador;

    public ServicioController(ServicioService servicioService, ReservaService reservaService, Notificador notificador) {
        this.servicioService = servicioService;
        this.reservaService = reservaService;
        this.notificador = notificador;
    }


    @GetMapping("/{idServicio}/consultar")
    public ResponseEntity<String> getServicioById(
            @PathVariable Long idServicio,
            @RequestParam Date fechaInicio,
            @RequestParam String destino
    ) {
        boolean isAvaible = servicioService.isServiceAvaible(idServicio, fechaInicio, destino);
        if (isAvaible) {
            return new ResponseEntity<>(
                    "Puedes contratar este servicio, se te cobrara $"
                    + servicioService.getServiceById(idServicio).getPrecio() * 0.1
                    + " en modo de adelanto",
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("No puedes contratar este servicio", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{idServicio}/contratar")
    public ResponseEntity<Notificacion> contratarServicio(
            @PathVariable Long idServicio,
            @RequestParam Date fechaInicio,
            @RequestParam String destino
    ) {
        boolean isAvaible = servicioService.isServiceAvaible(idServicio, fechaInicio, destino);
        if (isAvaible) {
            Servicio servicio = servicioService.getServiceById(idServicio);
            Notificacion noti = reservaService.addReserva(servicio, fechaInicio);
            return new ResponseEntity<>(noti, HttpStatus.CREATED);
        } else {
            Notificacion notificacion = new Notificacion("No puedes contratar este servicio", new Date(), null);
            return new ResponseEntity<>(notificador.notificar(notificacion) ,HttpStatus.BAD_REQUEST);
        }
    }

}
