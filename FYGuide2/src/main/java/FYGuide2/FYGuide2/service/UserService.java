package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.User;
import FYGuide2.FYGuide2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
