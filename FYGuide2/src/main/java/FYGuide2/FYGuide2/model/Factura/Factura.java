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

    @Transient
    private AdapterPagos adapterPagos;


    public Factura(Long turista, Date fecha, double subtotal, double comision, double importeFinal, String estado) {
        this.turista = turista;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.comision = comision;
        this.importeFinal = importeFinal;
        this.estado = estado;
    }


    public String pagar(){
        if (estado.equals("Pagado")){
            return "La factura ya fue pagada";
        }
        setEstado("Pagado");
        setAdapterPagos(new Stripe());
        return adapterPagos.pagar(importeFinal, fecha);
    }




}
