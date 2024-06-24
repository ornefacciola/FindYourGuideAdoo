package FYGuide2.FYGuide2.rest;

import FYGuide2.FYGuide2.model.Factura.Factura;
import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;
import FYGuide2.FYGuide2.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("facturas")
public class FacturaController {
    //private final ServicioService servicioService;
    private final FacturaService facturaService;
    private final Notificador notificador;

    public FacturaController(FacturaService facturaService, Notificador notificador) {
        this.facturaService = facturaService;
        this.notificador = notificador;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Factura>> getAllFacturas() {
        Iterable<Factura> facturas = facturaService.getAllFacturas();
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    @PutMapping("/{idFactura}/pagar")
    public ResponseEntity<String> pagarFactura(@PathVariable Long idFactura) {
        Factura factura = facturaService.getFacturaById(idFactura);

        if (factura != null) {
            String noti = facturaService.pagarFactura(factura);
            return new ResponseEntity<>(noti, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}