package FYGuide2.FYGuide2.rest;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.User;
import FYGuide2.FYGuide2.rest.DTO.UserDTO;
import FYGuide2.FYGuide2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//entiende que esta clase es un controller

public class UserController {
    //UserController será un solo singleton, autoimportame la clase userService, en main no hace new UserController gracias al autowire. Inyecta la dependencia, vincula clases.
    @Autowired
    private UserService userService;

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

    /*@RequestMapping(value="/guias", method=RequestMethod.GET)
    public List<Guia> getGuias(){
        return null;
    };*/
}

