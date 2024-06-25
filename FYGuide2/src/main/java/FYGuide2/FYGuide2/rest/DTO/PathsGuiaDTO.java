package FYGuide2.FYGuide2.rest.DTO;

import FYGuide2.FYGuide2.model.Servicio;

public class PathsGuiaDTO {
    private Long guiaId;
    private Servicio servicio;
    private Long servicioId;

    public Long getGuiaId() {
        return guiaId;
    }

    public void setGuiaId(Long guiaId) {
        this.guiaId = guiaId;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }
}
