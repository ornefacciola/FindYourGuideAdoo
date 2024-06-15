package FYGuide2.FYGuide2.rest.DTO;

import lombok.Getter;
import lombok.Setter;
//Sirve para que al crear, solicitar qué atributos ingresar, como nombre dni, no id etc.
//modelos que crean en el controller. Se espera un userDTO. El controller es un adapter.
@Getter
@Setter

public class UserDTO {
    private String name;
}
