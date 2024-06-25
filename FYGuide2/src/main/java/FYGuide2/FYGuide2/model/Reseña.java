package FYGuide2.FYGuide2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "reseñas")
public class Reseña {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "turista_id")
    private Long turistaId;

    @Column(name = "guia_id")
    private Long guiaId;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "calificacion")
    private Integer calificacion;

    public Reseña(Long turistaId, Long guiaId, String comentario, Integer calificacion) {
        this.turistaId = turistaId;
        this.guiaId = guiaId;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }
}
