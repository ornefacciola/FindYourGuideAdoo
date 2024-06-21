package FYGuide2.FYGuide2.model;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Turista extends User{
    public Turista(){}

    public Turista(Long userId, String email, String username, String userPassword, String firstName, String lastName, Integer dni, Integer celular, String sex, String profile_pic) {
        super(userId, email, username, userPassword, firstName, lastName, dni, celular, sex, profile_pic);
    }


}
