package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.model.User;
import FYGuide2.FYGuide2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public Guia addServiceToGuia(Long guiaId, Servicio servicio) {
        Guia guia = (Guia) userRepository.findById(guiaId).orElse(null);
        if (guia != null) {
            guia.getServiciosOfrecidos().add(servicio);
            userRepository.save(guia);
        }
        return guia;
    }

    public Guia removeServiceFromGuia(Long guiaId, Long servicioId) {
        Guia guia = (Guia) userRepository.findById(guiaId).orElse(null);
        if (guia != null) {
            guia.getServiciosOfrecidos().removeIf(s -> s.getId().equals(servicioId));
            userRepository.save(guia);
        }
        return guia;
    }


}
