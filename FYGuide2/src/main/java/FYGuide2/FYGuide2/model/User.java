package FYGuide2.FYGuide2.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "users")
public abstract class User implements UserDetails {
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

        @Column(name = "dni")
        private Integer dni;

        @Column(name = "celular")
        private Integer celular;

        @Column(name = "sex")
        private String sex;

        @Column(name = "profile_pic")
        private String profilePic;



        public User(String email, Long userId, String userPassword, String username, String sex, String firstName, String lastName, Integer dni, Integer celular, String profilePic) {
                this.email = email;
                this.userId = userId;
                this.userPassword = userPassword;
                this.username = username;
                this.sex = sex;
                this.firstName = firstName;
                this.lastName = lastName;
                this.dni = dni;
                this.celular = celular;
                this.profilePic = profilePic;
        }


        @Override
        public String getUsername() {
                return getEmail();
        }


        @Override
        public String getPassword() {
                return getUserPassword();
        }
        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }
}
