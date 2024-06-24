package FYGuide2.FYGuide2.model;


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
@Table(name = "viajes_finalizados")
public class ViajesFinalizados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "turista_id")
    private Long turistaId;

    @Column(name = "guia_id")
    private Long guiaId;

    @Column(name = "servicio_id")
    private Long servicioId;

    @Column(name = "destino")
    private String destino;

    public ViajesFinalizados(Date fecha, Long turistaId, Long guiaId, Long servicioId, String destino) {
        this.fecha = fecha;
        this.turistaId = turistaId;
        this.guiaId = guiaId;
        this.servicioId = servicioId;
        this.destino = destino;
    }

}
