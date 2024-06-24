package FYGuide2.FYGuide2.model.Reserva;

import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;

import java.util.Date;

public class EstadoRechazado implements EstadoReserva{

        /*private final Notificador notificador;

        public EstadoRechazado(Notificador notificador){
            this.notificador = notificador;
        }*/

        public String status = "rejected";
        @Override
        public Notificacion aceptarReserva(Reserva reserva) {
            Notificacion notificacion = new Notificacion("La reserva ya ha sido rechazada", new Date(), reserva.getTuristaId());
            return notificacion;
        }

        @Override
        public Notificacion rechazarReserva(Reserva reserva) {
            Notificacion notificacion = new Notificacion("La reserva ya ha sido rechazada", new Date(),reserva.getTuristaId());
            return notificacion;
        }

        @Override
        public Notificacion cancelarReserva(Reserva reserva) {
            Notificacion noti = new Notificacion("La reserva ya ha sido rechazada", new Date(), reserva.getTuristaId());
            return noti;
        }

        @Override
        public Notificacion finalizarReserva(Reserva reserva) {
            Notificacion noti = new Notificacion("La reserva ya ha sido rechazada", new Date(), reserva.getTuristaId());
            return noti;
        }
}
