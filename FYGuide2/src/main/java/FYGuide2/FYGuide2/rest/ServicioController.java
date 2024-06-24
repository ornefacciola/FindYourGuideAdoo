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


}
