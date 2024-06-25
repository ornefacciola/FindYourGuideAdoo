package FYGuide2.FYGuide2.rest.config;


import FYGuide2.FYGuide2.model.Guia;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("guias/delete","guias/all").hasRole("GUIA")
                        .requestMatchers("guias/services/add","guias/services/remove","guias/changeProfile").hasRole("GUIA")
                        .requestMatchers("guias/authenticate","guias/register", "guias/search").permitAll()
                        .requestMatchers("guias/{idServicio}/consultar").hasAnyRole("GUIA", "TURISTA")
                        .requestMatchers("guias/{idGuia}/reseña/{idTurista}").hasRole("TURISTA")
                        .requestMatchers("turistas/{turistaId}/facturas").hasRole("TURISTA")
                        .requestMatchers("turistas/changeProfile","turistas/delete").hasRole("TURISTA")
                        .requestMatchers("turistas/all","turistas/getById").hasAnyRole("GUIA", "TURISTA")
                        .requestMatchers("users/{userId}/trofeo").hasAnyRole("GUIA","TURISTA")
                        .requestMatchers("turistas/authenticate","turistas/register").permitAll()
                        .requestMatchers("reservas/all","reservas/{idReserva}").hasAnyRole("GUIA","TURISTA")
                        .requestMatchers("reservas/{idReserva}/aceptar","reservas/{idReserva}/rechazar","reservas/{idReserva}/finalizar").hasRole("GUIA")
                        .requestMatchers("reservas/{idServicio}/contratar/{idTurista}").hasRole("TURISTA")
                        .requestMatchers("turistas/{turistaId}/facturas").hasRole("TURISTA")
                        .requestMatchers("servicios/all","servicios/getById").permitAll()
                        .requestMatchers("viajes-finalizados/guia/{guiaId}").hasAnyRole("GUIA", "TURISTA")
                        .requestMatchers("viajes-finalizados/turista/{turistaId}").hasRole("TURISTA")
                        .requestMatchers("facturas/all","facturas/{idFactura}/pagar-total","facturas/{idFactura}/pagar-reserva","facturas/{idFactura}/pagar-punitorio").hasRole("TURISTA")
                        .anyRequest()
                        .permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
