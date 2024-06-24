package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.Factura.Factura;
import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.repository.FacturaRepository;
import FYGuide2.FYGuide2.repository.ReservaRepository;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public Iterable<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    public Factura getFacturaById(Long idFactura) {
        return facturaRepository.findById(idFactura).orElse(null);
    }

    public String pagarFactura(Factura factura) {
        String notificacion = factura.pagar();
        facturaRepository.save(factura);
        return notificacion;
    }
}
