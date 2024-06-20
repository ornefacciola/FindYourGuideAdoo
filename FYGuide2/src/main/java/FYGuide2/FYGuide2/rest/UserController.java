package FYGuide2.FYGuide2.rest;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.User;
import FYGuide2.FYGuide2.rest.DTO.UserDTO;
import FYGuide2.FYGuide2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
//entiende que esta clase es un controller
@AllArgsConstructor
@RequestMapping("users")


public class UserController {
    //UserController será un solo singleton, autoimportame la clase userService, en main no hace new UserController gracias al autowire. Inyecta la dependencia, vincula clases.
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*
    @RequestMapping( value ="/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getAllUsers();
    };

    //COMO UN ADAPTER DEL USERDTO
    @RequestMapping(value = "/users", method=RequestMethod.POST)
    public User createUser(UserDTO userDTO){
        User user= new User(userDTO.getName());
        return userService.createUser(user);
    }

    @RequestMapping(value="/guias", method=RequestMethod.GET)
    public List<Guia> getGuias(){
        return null;
    };*/
}

