package FYGuide2.FYGuide2.rest;

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
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{guiaId}/services/add")
    public ResponseEntity<Guia> addServiceToGuia(@PathVariable Long guiaId, @RequestBody Servicio servicio) {
        Guia guia = userService.addServiceToGuia(guiaId, servicio);
        return new ResponseEntity<>(guia, HttpStatus.OK);
    }

    @DeleteMapping("/{guiaId}/services/remove/{servicioId}")
    public ResponseEntity<Guia> removeServiceFromGuia(@PathVariable Long guiaId, @PathVariable Long servicioId) {
        Guia guia = userService.removeServiceFromGuia(guiaId, servicioId);
        return new ResponseEntity<>(guia, HttpStatus.OK);
    }
}
