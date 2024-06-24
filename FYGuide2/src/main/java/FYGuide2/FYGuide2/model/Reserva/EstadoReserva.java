package FYGuide2.FYGuide2.model.Reserva;

import FYGuide2.FYGuide2.model.Notificador.Notificacion;

public interface EstadoReserva {

    Notificacion aceptarReserva(Reserva reserva);
    Notificacion rechazarReserva(Reserva reserva);
    Notificacion cancelarReserva(Reserva reserva);
    Notificacion finalizarReserva(Reserva reserva);
}
