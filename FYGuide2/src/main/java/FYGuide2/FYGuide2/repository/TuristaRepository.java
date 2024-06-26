package FYGuide2.FYGuide2.repository;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface TuristaRepository extends CrudRepository<Turista, Long> {
    Optional<Turista> findByEmail(String mail);
}
