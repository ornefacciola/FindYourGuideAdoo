package FYGuide2.FYGuide2.rest;

import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.model.User;
import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/trofeo")
    public ResponseEntity<String> getTrofeo(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            String trofeo = user.getTrofeo();
            if (trofeo != null) {
                return new ResponseEntity<>(trofeo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No tiene un trofeo", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("No se encontro al usuario", HttpStatus.NOT_FOUND);
        }
    }

}
