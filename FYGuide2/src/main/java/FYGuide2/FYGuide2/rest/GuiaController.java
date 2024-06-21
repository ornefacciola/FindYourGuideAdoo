package FYGuide2.FYGuide2.rest;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.service.GuiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("guias")
public class GuiaController {

    private final GuiaService guiaService;

    @Autowired
    public GuiaController(GuiaService guiaService) {
        this.guiaService = guiaService;
    }

    @PostMapping("/add")
    public ResponseEntity<Guia> addGuia(@RequestBody Guia guia) {
        Guia savedGuia = guiaService.addGuia(guia);
        if (savedGuia != null) {
            return new ResponseEntity<>(savedGuia, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @DeleteMapping("/delete/{guiaId}")
    public ResponseEntity<Void> deleteGuia(@PathVariable Long guiaId) {
        guiaService.deleteGuia(guiaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{guiaId}/services/add")
    public ResponseEntity<Guia> addServiceToGuia(@PathVariable Long guiaId, @RequestBody Servicio servicio) {
        Guia guia = guiaService.addServiceToGuia(guiaId, servicio);
        if (guia != null) {
            return new ResponseEntity<>(guia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{guiaId}/services/remove/{servicioId}")
    public ResponseEntity<Guia> removeServiceFromGuia(@PathVariable Long guiaId, @PathVariable Long servicioId) {
        Guia guia = guiaService.removeServiceFromGuia(guiaId, servicioId);
        if (guia != null) {
            return new ResponseEntity<>(guia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/changeProfile/{userId}")
    public ResponseEntity<Void> changeProfile(@PathVariable Long userId) {
        ResponseEntity<Void> response = guiaService.ChangeProfile(userId);

        return response;

    };
}
