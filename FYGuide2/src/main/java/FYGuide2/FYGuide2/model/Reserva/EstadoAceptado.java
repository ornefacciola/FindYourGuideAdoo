package FYGuide2.FYGuide2.model.Reserva;

import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;



public class EstadoAceptado implements EstadoReserva{

    /*private final Notificador notificador;

    public EstadoAceptado(Notificador notificador) {
        this.notificador = notificador;
    }*/

    @Override
    public Notificacion aceptarReserva(Reserva reserva) {
        Notificacion notificacion = new Notificacion("La reserva ya ha sido aceptada", new Date(), null);
        return notificacion;
    }

    @Override
    public Notificacion rechazarReserva(Reserva reserva) {
        Notificacion notificacion = new Notificacion(
                "No puedes rechazar una reserva que ya ha sido aceptada",
                new Date(),
                null);
        return notificacion;
    }

    @Override
    public Notificacion cancelarReserva(Reserva reserva) {
        reserva.setEstadoReserva(new EstadoCancelado());
        reserva.setEstado("Cancelado");
        Notificacion noti = new Notificacion(
                "Tu reserva ha sido cancelada, se te aplicara un punitorio de $" + reserva.calcularPunitorio(),
                new Date(),
                reserva.getTurista());
        //notificador.notificar(noti);
        return noti;
    }

    @Override
    public Notificacion finalizarReserva(Reserva reserva) {
        Notificacion noti = new Notificacion("No se puede finalizar una reserva que no ha sido aceptada", new Date(), null);
        return noti;
    }
}
