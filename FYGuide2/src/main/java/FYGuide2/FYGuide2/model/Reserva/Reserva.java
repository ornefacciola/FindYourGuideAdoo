package FYGuide2.FYGuide2.model.Reserva;


import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.model.Turista;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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


    public void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
        this.estado = estadoReserva.toString();
    }



}
