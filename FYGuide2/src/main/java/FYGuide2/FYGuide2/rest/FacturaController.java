package FYGuide2.FYGuide2.rest;

import FYGuide2.FYGuide2.model.Factura.Factura;
import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;
import FYGuide2.FYGuide2.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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