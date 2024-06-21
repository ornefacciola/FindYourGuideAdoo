package FYGuide2.FYGuide2.repository;

import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository

public interface TuristaRepository extends CrudRepository<Turista, Long> {
}
