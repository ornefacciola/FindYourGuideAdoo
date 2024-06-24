package FYGuide2.FYGuide2.rest;


import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.service.ReservaService;
import FYGuide2.FYGuide2.service.ServicioService;
import FYGuide2.FYGuide2.service.TuristaService;
import FYGuide2.FYGuide2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final ReservaService reservaService;
    private final TuristaService turistaService;

    private final Notificador notificador;

    public ServicioController(ServicioService servicioService, ReservaService reservaService, TuristaService turistaService, Notificador notificador) {
        this.servicioService = servicioService;
        this.reservaService = reservaService;
        this.turistaService = turistaService;
        this.notificador = notificador;
    }


    @GetMapping("/all")
    public ResponseEntity<Iterable<Servicio>> getAllServicios() {
        Iterable<Servicio> servicios = servicioService.getAllServices();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }


    @GetMapping("/{idServicio}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long idServicio) {
        Servicio servicio = servicioService.getServiceById(idServicio);
        if (servicio != null) {
            return new ResponseEntity<>(servicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    /*@GetMapping("/{idServicio}/consultar")
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

    @PostMapping("/{idServicio}/contratar/{idTurista}")
    public ResponseEntity<Notificacion> contratarServicio(
            @PathVariable Long idServicio,
            @PathVariable Long idTurista,
            @RequestParam Date fechaInicio,
            @RequestParam String destino
    ) {
        boolean isAvaible = servicioService.isServiceAvaible(idServicio, fechaInicio, destino);
        Turista turista = turistaService.getTuristaById(idTurista);
        if (isAvaible) {
            Servicio servicio = servicioService.getServiceById(idServicio);
            Notificacion noti = reservaService.addReserva(servicio, fechaInicio, turista);
            return new ResponseEntity<>(noti, HttpStatus.CREATED);
        } else {
            Notificacion notificacion = new Notificacion("No puedes contratar este servicio", new Date(), null);
            return new ResponseEntity<>(notificador.notificar(notificacion) ,HttpStatus.BAD_REQUEST);
        }
    }*/

}
