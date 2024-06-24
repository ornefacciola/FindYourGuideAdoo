package FYGuide2.FYGuide2.rest;

import FYGuide2.FYGuide2.model.ViajesFinalizados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import FYGuide2.FYGuide2.service.ViajesFinalizadosService;

@RestController
@RequestMapping("viajes-finalizados")
public class ViajesFinalizadosController {

    private final ViajesFinalizadosService viajesFinalizadosService;

    public ViajesFinalizadosController(ViajesFinalizadosService viajesFinalizadosService) {
        this.viajesFinalizadosService = viajesFinalizadosService;
    }


    @GetMapping("/guia/{guiaId}")
    public ResponseEntity<Iterable<ViajesFinalizados>> getViajesFinalizadosByGuiaId(@PathVariable Long guiaId) {
        Iterable<ViajesFinalizados> viajes = viajesFinalizadosService.getAllViajesFinalizadosGuia(guiaId);
        return new ResponseEntity<>(viajes, HttpStatus.OK);
    }

    @GetMapping("/turista/{turistaId}")
    public ResponseEntity<Iterable<ViajesFinalizados>> getViajesFinalizadosByTuristaId(@PathVariable Long turistaId) {
        Iterable<ViajesFinalizados> viajes = viajesFinalizadosService.getAllViajesFinalizadosTurista(turistaId);
        return new ResponseEntity<>(viajes, HttpStatus.OK);
    }
}
