    package FYGuide2.FYGuide2.model;

    import FYGuide2.FYGuide2.model.Reserva.Reserva;
    import com.fasterxml.jackson.annotation.*;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import lombok.experimental.SuperBuilder;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.Collections;
    import java.util.List;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @Entity
    public class Guia extends User {
        private String licencia;

        @ElementCollection
        @CollectionTable(name = "guia_locations", joinColumns = @JoinColumn(name = "guia_id"))
        @Column(name = "location")
        private List<String> locations;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "guia_id")
        private List<Servicio> serviciosOfrecidos = new ArrayList<>();   // Initialize to avoid NPE

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "guia_id")
        private List<Reserva> reservas = new ArrayList<>();

        public Guia(Long userId, String email, String username, String userPassword, String firstName, String lastName, Integer dni, Integer celular, String sex, String profilePic, String licencia, List<String> locations) {
            super(userId, email, username, userPassword, firstName, lastName, dni, celular, sex, profilePic);
            this.licencia = licencia;
            this.locations = locations;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_GUIA"));
        }


    }
