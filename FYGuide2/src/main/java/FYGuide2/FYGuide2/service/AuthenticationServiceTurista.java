package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.repository.TuristaRepository;
import FYGuide2.FYGuide2.rest.auth.*;
import FYGuide2.FYGuide2.rest.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceTurista {

    private final TuristaRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterTuristaRequest request) {
        Turista user = Turista.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .userPassword(this.passwordEncoder.encode(request.getUserPassword()))
                .username(request.getUsername())
                .dni(request.getDni())
                .celular(request.getCelular())
                .sex(request.getSex())
                .profilePic(request.getProfilePic())
                .cantReseñas(0)
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getUserPassword()));

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
