package FYGuide2.FYGuide2.model.Factura;

import FYGuide2.FYGuide2.model.Notificador.Notificacion;

import java.util.Date;

public interface AdapterPagos {

    String pagar(Double importeFinal, Date fecha);

}
