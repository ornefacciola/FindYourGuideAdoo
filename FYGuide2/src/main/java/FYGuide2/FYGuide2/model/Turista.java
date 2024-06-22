package FYGuide2.FYGuide2.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@SuperBuilder
@Entity
public class Turista extends User{
    public Turista(){}

    public Turista(Long userId, String email, String username, String userPassword, String firstName, String lastName, Integer dni, Integer celular, String sex, String profile_pic) {
        super(userId, email, username, userPassword, firstName, lastName, dni, celular, sex, profile_pic);
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return an empty collection since Turista does not have any roles
        return Collections.emptyList();
    }
}
