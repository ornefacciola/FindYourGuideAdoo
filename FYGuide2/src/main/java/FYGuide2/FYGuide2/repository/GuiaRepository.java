package FYGuide2.FYGuide2.repository;

import FYGuide2.FYGuide2.model.Guia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuiaRepository extends CrudRepository<Guia, Long> {

}
