package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.User;
import FYGuide2.FYGuide2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
@Service //hace un Singleton, instancia una sola clase de Service y encuentra los modelos de User, usa el mismo objeto en memoria
//se encarga de ej encontraar todos los usuarios


public class UserService {

    @Autowired
    UserRepository userRepository;
    public User createUser(User user){
        return user;
    }
    public List<User> getAllUsers(){
            return userRepository.findAll();

    };
}
