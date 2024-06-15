package FYGuide2.FYGuide2.repository;

import FYGuide2.FYGuide2.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findAll();//SELECT * FROM Users

    User findById(long id);
}
