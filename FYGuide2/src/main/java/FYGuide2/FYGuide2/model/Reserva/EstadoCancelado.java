package FYGuide2.FYGuide2.model.Reserva;

import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;

import java.util.Date;


public class EstadoCancelado implements EstadoReserva{

    /*private final Notificador notificador;

    public EstadoCancelado(Notificador notificador){
        this.notificador = notificador;
    }*/

    @Override
    public Notificacion aceptarReserva(Reserva reserva) {
        Notificacion notificacion = new Notificacion("La reserva ya ha sido cancelada", new Date(), null);
        return notificacion;
    }

    @Override
    public Notificacion rechazarReserva(Reserva reserva) {
        Notificacion notificacion = new Notificacion("La reserva ya ha sido cancelada", new Date(),null);
        return notificacion;
    }

    @Override
    public Notificacion cancelarReserva(Reserva reserva) {
        Notificacion noti = new Notificacion("La reserva ya ha sido cancelada", new Date(), reserva.getTurista());
        return noti;
    }

    @Override
    public Notificacion finalizarReserva(Reserva reserva) {
        Notificacion noti = new Notificacion("La reserva ya ha sido cancelada", new Date(), null);
        return noti;
    }
}
