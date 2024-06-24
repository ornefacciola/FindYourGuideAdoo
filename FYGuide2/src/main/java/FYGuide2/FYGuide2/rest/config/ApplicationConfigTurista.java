package FYGuide2.FYGuide2.rest.config;/*
import FYGuide2.FYGuide2.repository.TuristaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfigTurista {

    private final TuristaRepository repository;

    @Bean
    public UserDetailsService turistaDetailsService() {
        return email -> repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Bean
    public AuthenticationProvider authenticationProviderTurista() {
        DaoAuthenticationProvider authenticationProviderTurista = new DaoAuthenticationProvider();
        authenticationProviderTurista.setUserDetailsService(turistaDetailsService());
        authenticationProviderTurista.setPasswordEncoder(passwordEncoderTurista());
        return authenticationProviderTurista;
    }


    @Bean
    public AuthenticationManager authenticationManagerTurista(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoderTurista() {
        return new BCryptPasswordEncoder();
    }
}
*/