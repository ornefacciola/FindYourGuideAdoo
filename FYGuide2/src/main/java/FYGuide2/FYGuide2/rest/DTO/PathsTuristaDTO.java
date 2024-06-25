package FYGuide2.FYGuide2.rest.DTO;

import FYGuide2.FYGuide2.model.Turista;
import org.springframework.data.jpa.domain.Specification;

public class PathsTuristaDTO {

    private Long turistaId;

    // Getters and Setters
    public Long getTuristaId() {
        return turistaId;
    }

    public void setTuristaId(Long turistaId) {
        this.turistaId = turistaId;
    }



}
