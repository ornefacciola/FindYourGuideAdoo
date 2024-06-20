package FYGuide2.FYGuide2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //tabla user.
@Table(name="users")
//mapea objetos a una tabla
//cada atributo una columna

public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userId;

        @Column(name = "email")
        private String email;

        @Column(name = "username")
        private String username;

        @Column(name = "user_password")
        private String userPassword;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;


}
