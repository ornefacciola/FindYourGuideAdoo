package FYGuide2.FYGuide2.model.Reserva;


import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Notificador.Notificacion;
import FYGuide2.FYGuide2.model.Notificador.Notificador;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.model.Turista;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turista_id")
    private Turista turista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guia_id")
    private Guia guia;

    @Column(name = "estado")
    private String estado;

    @Column(name = "anticipo")
    private Double anticipo;

    @Transient
    private EstadoReserva estadoReserva;

    @Transient
    private Notificador notificador;

    public Reserva(Long id, Date fechaInicio, Servicio servicio, Turista turista, Double anticipo) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.servicio = servicio;
        this.turista = turista;
        this.anticipo = anticipo;
        this.guia = servicio.getGuia();
        this.estado = "Pendiente";
    }


    public Reserva(Servicio servicio, Date fechaInicio, Double anticipo) {
        this.servicio = servicio;
        this.fechaInicio = fechaInicio;
        this.anticipo = anticipo;
        this.guia = servicio.getGuia();
        this.estado = "Reservado";
    }

    @PostLoad
    public void postLoad() {
        this.estadoReserva = getEstadoReservaInstance(this.estado);
    }



    public void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }



    public Notificacion aceptar() {
        Notificacion noti = this.estadoReserva.aceptarReserva(this);
        return noti;
    };

    public Notificacion rechazar() {
        Notificacion noti = this.estadoReserva.rechazarReserva(this);
        return noti;
    };

    public Notificacion cancelar() {
        Notificacion noti = this.estadoReserva.cancelarReserva(this);
        return noti;
    };

    public Notificacion finalizar() {
        Notificacion noti = this.estadoReserva.finalizarReserva(this);
        return noti;
    };

    public Double calcularPunitorio (){
        Date fechaDelViaje = this.fechaInicio;


        Calendar fechaHoyCal = Calendar.getInstance();
        fechaHoyCal.set(Calendar.HOUR_OF_DAY, 0);
        fechaHoyCal.set(Calendar.MINUTE, 0);
        fechaHoyCal.set(Calendar.SECOND, 0);
        fechaHoyCal.set(Calendar.MILLISECOND, 0);
        Date fechaHoy = fechaHoyCal.getTime();


        Calendar fechaReservaCal = Calendar.getInstance();
        fechaReservaCal.setTime(fechaDelViaje);
        fechaReservaCal.set(Calendar.HOUR_OF_DAY, 0);
        fechaReservaCal.set(Calendar.MINUTE, 0);
        fechaReservaCal.set(Calendar.SECOND, 0);
        fechaReservaCal.set(Calendar.MILLISECOND, 0);
        Date fechaReserva = fechaReservaCal.getTime();



        long diff = fechaReserva.getTime() - fechaHoy.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if (diffDays > 15){
            return this.servicio.getPrecio() * 0.3;
        } else if (diffDays > 7){
            return this.servicio.getPrecio() * 0.5;
        } else if (diffDays > 3){
            return this.servicio.getPrecio() * 0.8;
        } else {
            return this.servicio.getPrecio();
        }

    }


    public EstadoReserva getEstadoReservaInstance(String estado) {
        switch (estado) {
            case "Reservado":
                return new EstadoReservado();
            case "Aceptado":
                return new EstadoAceptado();
            case "Rechazado":
                return new EstadoRechazado();
            case "Cancelado":
                return new EstadoCancelado();
            case "Finalizado":
                return new EstadoFinalizado();
            default:
                throw new IllegalArgumentException("Estado desconocido: " + estado);
        }
    }
}
