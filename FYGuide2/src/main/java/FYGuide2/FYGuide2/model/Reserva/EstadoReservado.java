package FYGuide2.FYGuide2.model.Reserva;

import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;

import java.util.Date;

public class EstadoReservado implements EstadoReserva{
    /*private final Notificador notificador;

    public EstadoReservado(Notificador notificador){
        this.notificador = notificador;
    }*/

    @Override
    public Notificacion aceptarReserva(Reserva reserva) {
        reserva.setEstadoReserva(new EstadoAceptado());
        reserva.setEstado("Aceptado");
        Notificacion notificacion = new Notificacion("Reserva aceptada", new Date(), null);
        //notificador.notificar(notificacion);
        return notificacion;
    }

    @Override
    public Notificacion rechazarReserva(Reserva reserva) {
        reserva.setEstadoReserva(new EstadoRechazado());
        reserva.setEstado("Rechazado");
        Notificacion notificacion = new Notificacion(
                "Reserva rechazada, se te ha depositado el anticipo",
                new Date(),
                null);
        //notificador.notificar(notificacion);
        reserva.setAnticipo(0.0);
        return notificacion;
    }

    @Override
    public Notificacion cancelarReserva(Reserva reserva) {
        Notificacion noti = new Notificacion("No se puede cancelar una reserva que no ha sido aceptada", new Date(), null);
        return noti;
    }

    @Override
    public Notificacion finalizarReserva(Reserva reserva) {
        Notificacion noti = new Notificacion("No se puede finalizar una reserva que no ha sido aceptada", new Date(), null);
        return noti;
    }
}
