package FYGuide2.FYGuide2.repository;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String mail);
}
