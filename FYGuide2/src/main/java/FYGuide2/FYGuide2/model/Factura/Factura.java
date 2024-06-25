package FYGuide2.FYGuide2.model.Factura;


import FYGuide2.FYGuide2.model.Notificador.Notificacion;
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
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "turista_id")
    private Long turista;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "subtotal")
    private double subtotal;

    @Column(name = "comision")
    private double comision;

    @Column(name = "importe_final")
    private double importeFinal;

    @Column(name = "estado")
    private String estado;

    @Column(name = "anticipo")
    private Double anticipo;

    @Column(name = "punitorio")
    private Double punitorio;

    @Transient
    private AdapterPagos adapterPagos;


    public Factura(Long turista, Date fecha, double subtotal, double comision, double importeFinal, double anticipo, double punitorio, String estado) {
        this.turista = turista;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.comision = comision;
        this.importeFinal = importeFinal;
        this.estado = estado;
        this.anticipo = anticipo;
        this.punitorio = punitorio;
    }


    public String pagarReserva() {
        if (estado.equals("Anticipo pagado")) {
            return "El anticipo ya fue pagado";
        }
        setEstado("Anticipo pagado");
        setAdapterPagos(new Stripe());
        return adapterPagos.pagarReserva(anticipo, fecha);
    }


    public String pagarTotal() {
        if (estado.equals("Pagado")) {
            return "La factura ya fue pagada";
        }
        if (estado.equals("Total no pagado")) {
            setEstado("Pagado");
            setAdapterPagos(new Stripe());
            return adapterPagos.pagarTotal(importeFinal, fecha);
        } else {
            return "No se puede pagar la factura, aun no finalizo el viaje";
        }

    }

    public String pagarPunitorio() {
        if (estado.equals("Punitorio pagado")) {
            return "El punitorio ya fue pagado";
        }

        if (estado.equals("Punitorio no pagado")) {
            setEstado("Pagado");
            setAdapterPagos(new Stripe());
            return adapterPagos.pagarTotal(punitorio, fecha);
        } else {
            return "No se puede pagar el punitorio, aun no se cancelo la reserva";
        }
    }




}
