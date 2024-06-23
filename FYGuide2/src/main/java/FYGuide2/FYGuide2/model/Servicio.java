package FYGuide2.FYGuide2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "duracion_servicio")
    private Integer duracionServicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guia_id")
    private Guia guia;

    public Servicio(Long id, Integer duracionServicio, String ciudad, String categoria, String descripcion, Double precio, String serviceName) {
        this.id = id;
        this.duracionServicio = duracionServicio;
        this.ciudad = ciudad;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.serviceName = serviceName;
    }

}