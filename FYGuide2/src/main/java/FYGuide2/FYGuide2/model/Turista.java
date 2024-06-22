package FYGuide2.FYGuide2.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
