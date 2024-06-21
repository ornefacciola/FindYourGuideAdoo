package FYGuide2.FYGuide2.rest;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.service.GuiaService;
import FYGuide2.FYGuide2.service.TuristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("turistas")
public class TuristaController {
    private final TuristaService turistaService;

    @Autowired
    public TuristaController(TuristaService turistaService) {
        this.turistaService = turistaService;
    }

    @PostMapping("/add")
    public ResponseEntity<Turista> addTurista(@RequestBody Turista turista) {
        Turista savedTurista = turistaService.addTurista(turista);
        if (savedTurista != null) {
            return new ResponseEntity<>(savedTurista, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{turistaId}")
    public ResponseEntity<Turista> getTuristaById(@PathVariable Long turistaId) {
        Turista turista = turistaService.getTuristaById(turistaId);
        if (turista != null) {
            return new ResponseEntity<>(turista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Turista>> getAllTuristas() {
        Iterable<Turista> turistas = turistaService.getAllTuristas();
        return new ResponseEntity<>(turistas, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{turistaId}")
    public ResponseEntity<Void> deleteTurista(@PathVariable Long turistaId) {
        turistaService.deleteTurista(turistaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
