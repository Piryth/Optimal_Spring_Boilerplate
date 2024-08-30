package fr.optimal.optimalshop.auth;


import fr.optimal.optimalshop.appUser.AppUser;
import fr.optimal.optimalshop.appUser.AppUserDto;
import fr.optimal.optimalshop.appUser.AppUserRepository;
import fr.optimal.optimalshop.config.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final static Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(AppUserDto appUserDto) throws Exception {
        //Création de l'utilisateur
        AppUser user = AppUser.builder()
                .username(appUserDto.username())
                .email(appUserDto.email())
                .password(passwordEncoder.encode(appUserDto.password()))
                .role("USER")
                .build();
        //Sauvegarde de l'utilisateur
        appUserRepository.save(user);
        //Génération du token
        String jwtToken = jwtService.generateToken(user);
        //Génération de la réponse
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        logger.log( Level.INFO,"Authentification de l'utilisateur : " + authenticationRequest.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        AppUser user = appUserRepository.findAppUserByUsername(authenticationRequest.getUsername()).orElseThrow( () -> new UsernameNotFoundException("User not found"));
        //Génération du token
        String jwtToken = jwtService.generateToken(user);
        //Génération de la réponse
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
