package FYGuide2.FYGuide2.model.Reserva;

import FYGuide2.FYGuide2.model.Notificador.Notificacion;

public interface EstadoReserva {

    public Notificacion aceptarReserva(Reserva reserva);
    public Notificacion rechazarReserva(Reserva reserva);
    public Notificacion cancelarReserva(Reserva reserva);
    public Notificacion finalizarReserva(Reserva reserva);
}
