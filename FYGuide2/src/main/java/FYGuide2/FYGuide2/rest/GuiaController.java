package FYGuide2.FYGuide2.rest;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.rest.DTO.PathsGuiaDTO;
import FYGuide2.FYGuide2.service.GuiaService;
import FYGuide2.FYGuide2.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("guias")
public class GuiaController {

    private final GuiaService guiaService;
    private final ServicioService servicioService;


    @Autowired
    public GuiaController(GuiaService guiaService, ServicioService servicioService) {
        this.guiaService = guiaService;
        this.servicioService = servicioService;
    }

    @GetMapping("/{guiaId}")
    public ResponseEntity<Guia> getGuiaById(@PathVariable Long guiaId) {
        Guia guia = guiaService.getGuiaById(guiaId);
        if (guia != null) {
            return new ResponseEntity<>(guia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Guia>> getAllGuias() {
        Iterable<Guia> guias = guiaService.getAllGuias();
        return new ResponseEntity<>(guias, HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteGuia(@RequestBody PathsGuiaDTO request) {
        guiaService.deleteGuia(request.getGuiaId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/services/add")
    public ResponseEntity<Optional<Guia>> addServiceToGuia(@RequestBody PathsGuiaDTO request) {
        Long guiaId = request.getGuiaId();
        Optional<Guia> servicioSaved = guiaService.addServiceToGuia(guiaId, request.getServicio());
        if (servicioSaved.isPresent()) {
            return new ResponseEntity<>(servicioSaved, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/services/remove")
    public ResponseEntity<Optional<Guia>> removeServiceFromGuia(@RequestBody PathsGuiaDTO request) {
        Long guiaId = request.getGuiaId();
        Long servicioId = request.getServicioId();

        Optional<Guia> servicioDeleted = guiaService.removeServiceFromGuia(guiaId, servicioId);
        if (servicioDeleted.isPresent()) {
            return new ResponseEntity<>(servicioDeleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/changeProfile")
    public ResponseEntity<Void> changeProfile(@RequestBody PathsGuiaDTO request) {
        Long userId = request.getGuiaId();

        ResponseEntity<Void> response = guiaService.changeProfile(userId);
        return response;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Guia>> searchGuias(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String location
    ) {

        List<Guia> guias = guiaService.searchGuias(firstName, lastName, location);
        return new ResponseEntity<>(guias, HttpStatus.OK);
    }


    @GetMapping("/{idServicio}/consultar")
    public ResponseEntity<String> getServicioById(
            @PathVariable Long idServicio,
            @RequestParam Date fechaInicio,
            @RequestParam String destino
    ) {
        boolean isAvaible = guiaService.isGuiaAvaible(idServicio, fechaInicio, destino);
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
}
