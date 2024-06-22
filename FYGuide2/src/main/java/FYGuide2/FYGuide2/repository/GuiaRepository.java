package FYGuide2.FYGuide2.repository;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuiaRepository extends CrudRepository<Guia, Long> {
    Optional<Guia> findByEmail(String mail);

}
