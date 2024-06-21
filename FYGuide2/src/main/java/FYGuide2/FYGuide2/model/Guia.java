package FYGuide2.FYGuide2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Guia extends User {
    private String licencia;

    @ElementCollection
    @CollectionTable(name = "guia_locations", joinColumns = @JoinColumn(name = "guia_id"))
    @Column(name = "location")
    private List<String> locations;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "guia_id")
    private List<Servicio> serviciosOfrecidos = new ArrayList<>(); // Initialize to avoid NPE

    public Guia(Long userId, String email, String username, String userPassword, String firstName, String lastName, Integer dni, Integer celular, String sex, String profilePic, String licencia, List<String> locations) {
        super(userId, email, username, userPassword, firstName, lastName, dni, celular, sex, profilePic);
        this.licencia = licencia;
        this.locations = locations;
    }
}
