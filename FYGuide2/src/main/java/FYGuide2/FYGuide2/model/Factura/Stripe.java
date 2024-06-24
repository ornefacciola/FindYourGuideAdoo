package FYGuide2.FYGuide2.model.Factura;

import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import org.springframework.stereotype.Service;

import java.util.Date;


public class Stripe implements AdapterPagos{

    public String pagar(){
        return "Pago realizado con Stripe";
    }

}
