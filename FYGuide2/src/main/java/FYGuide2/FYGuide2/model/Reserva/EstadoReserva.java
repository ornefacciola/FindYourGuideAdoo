package FYGuide2.FYGuide2.model.Reserva;

public interface EstadoReserva {

    public String aceptar(Reserva reserva);
    public String rechazar(Reserva reserva);
    public String cancelar(Reserva reserva);
    public String finalizar(Reserva reserva);
}
