package FYGuide2.FYGuide2.model;

import com.fasterxml.jackson.annotation.*;
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

    @Column(name = "duracion_servicio")
    private Integer duracionServicio;

    @Column(name = "guia_id")
    private Long guiaId;

    public Servicio(Integer duracionServicio, String categoria, String descripcion, Double precio, String serviceName) {
        this.duracionServicio = duracionServicio;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.serviceName = serviceName;
    }

}