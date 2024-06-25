package FYGuide2.FYGuide2.rest.DTO;

import FYGuide2.FYGuide2.model.Guia;
import org.springframework.data.jpa.domain.Specification;


public class GuiaDTO {

    public static Specification<Guia> hasFirstName(String firstName) {
        return (guia, cq, cb) -> firstName == null ? null : cb.equal(guia.get("firstName"), firstName);
    }

    public static Specification<Guia> hasLastName(String lastName) {
        return (guia, cq, cb) -> lastName == null ? null : cb.equal(guia.get("lastName"), lastName);
    }

    public static Specification<Guia> hasCity(String location) {
        return (guia, cq, cb) -> location == null ? null : cb.isMember(location, guia.get("locations"));
    }

    public static Specification<Guia> rating(Double rating) {
        return (guia, cq, cb) -> rating == null ? null : cb.greaterThanOrEqualTo(guia.get("puntuacionTotal"), rating);
    }




}


