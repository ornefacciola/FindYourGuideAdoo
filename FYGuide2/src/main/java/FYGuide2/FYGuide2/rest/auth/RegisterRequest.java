package FYGuide2.FYGuide2.rest.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String userPassword;
    private Integer dni;
    private Integer celular;
    private String sex;
    private String profilePic;
    private String licencia;  // Guia specific
    private List<String> locations;  // Guia specific


}
