package FYGuide2.FYGuide2.service;


import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.repository.GuiaRepository;
import FYGuide2.FYGuide2.repository.UserRepository;
import FYGuide2.FYGuide2.rest.auth.AuthenticationRequest;
import FYGuide2.FYGuide2.rest.auth.AuthenticationResponse;
import FYGuide2.FYGuide2.rest.auth.RegisterRequest;
import FYGuide2.FYGuide2.rest.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final GuiaRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Guia user = Guia.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .userPassword(this.passwordEncoder.encode(request.getUserPassword()))
                .username(request.getUsername())
                .dni(request.getDni())
                .celular(request.getCelular())
                .sex(request.getSex())
                .profilePic(request.getProfilePic())
                .licencia(request.getLicencia())
                .locations(request.getLocations())
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
