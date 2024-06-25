package FYGuide2.FYGuide2.model.Factura;

import FYGuide2.FYGuide2.model.Notificador.Notificacion;

import java.util.Date;

public interface AdapterPagos {

    String pagarReserva(Double importeFinal, Date fecha);
    String pagarTotal(Double importeFinal, Date fecha);
    String pagarPunitorio(Double importeFinal, Date fecha);
}
