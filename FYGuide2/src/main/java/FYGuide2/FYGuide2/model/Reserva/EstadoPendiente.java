package FYGuide2.FYGuide2.model.Reserva;

import FYGuide2.FYGuide2.model.Factura.Factura;
import FYGuide2.FYGuide2.model.Notificador.Notificacion;

import java.util.Date;

public class EstadoPendiente implements EstadoReserva {

    public String status = "pendiente";

    @Override
    public Notificacion aceptarReserva(Reserva reserva) {
        Factura factura = new Factura(
                reserva.getTuristaId(),
                new Date(),
                reserva.calcularSubtotal(),
                0.0,
                0.0,
                //reserva.calcularComision(),
                //reserva.calcularImporteFinal(),
                reserva.getAnticipo(),
                "Reserva no pagada");


        reserva.setFactura(factura);
        reserva.setEstadoReserva(new EstadoAceptado());
        reserva.setEstado("Aceptado");

        Notificacion notificacion = new Notificacion("Reserva aceptada, se te envio la factura del anticipo", new Date(), reserva.getTuristaId());//En vez de null tiene que ir el turista
        return notificacion;
    }

    @Override
    public Notificacion rechazarReserva(Reserva reserva) {
        reserva.setEstadoReserva(new EstadoRechazado());
        reserva.setEstado("Rechazado");
        Notificacion notificacion = new Notificacion(
                "Reserva rechazada",
                new Date(),
                reserva.getTuristaId());
        //notificador.notificar(notificacion);
        return notificacion;
    }

    @Override
    public Notificacion cancelarReserva(Reserva reserva) {
        reserva.setEstadoReserva(new EstadoCancelado());
        reserva.setEstado("Cancelado");
        Notificacion noti = new Notificacion("Tu reserva fue cancelada, no se te cobrara nada", new Date(), reserva.getTuristaId());
        return noti;
    }

    @Override
    public Notificacion finalizarReserva(Reserva reserva) {
        Notificacion noti = new Notificacion("No se puede finalizar una reserva que no ha sido aceptada", new Date(), reserva.getTuristaId());
        return noti;
    }

}
