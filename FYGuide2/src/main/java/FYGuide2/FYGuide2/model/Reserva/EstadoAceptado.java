package FYGuide2.FYGuide2.model.Reserva;

import FYGuide2.FYGuide2.model.Factura.Factura;
import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.ViajesFinalizados;
import FYGuide2.FYGuide2.repository.ViajesFinalizadosRepository;

import java.util.Date;



public class EstadoAceptado implements EstadoReserva{

    public String status = "accepted";


    @Override
    public Notificacion aceptarReserva(Reserva reserva) {
        Notificacion notificacion = new Notificacion("La reserva ya ha sido aceptada", new Date(), reserva.getTuristaId());
        return notificacion;
    }

    @Override
    public Notificacion rechazarReserva(Reserva reserva) {
        Notificacion notificacion = new Notificacion(
                "No puedes rechazar una reserva que ya ha sido aceptada",
                new Date(),
                reserva.getTuristaId());
        return notificacion;
    }

    @Override
    public Notificacion cancelarReserva(Reserva reserva) {
        reserva.setEstadoReserva(new EstadoCancelado());
        reserva.setEstado("Cancelado");
        Notificacion noti = new Notificacion(
                "Tu reserva ha sido cancelada, se te aplicara un punitorio de $" + reserva.calcularPunitorio(),
                new Date(),
                reserva.getTuristaId());
        //notificador.notificar(noti);
        return noti;
    }

    @Override
    public Notificacion finalizarReserva(Reserva reserva) {
        Factura factura = new Factura(
                reserva.getTuristaId(),
                new Date(),
                reserva.calcularSubtotal(),
                reserva.calcularComision(),
                reserva.calcularImporteFinal(),
                "No pagada");


        reserva.setFactura(factura);
        reserva.setEstadoReserva(new EstadoFinalizado());
        reserva.setEstado("Finalizado");


        Notificacion noti = new Notificacion("Tu viaje ha finalizado, se te envio la factura", new Date(), reserva.getTuristaId());
        return noti;
    }
}
