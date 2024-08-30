package fr.optimal.optimalshop.appUser;


import fr.optimal.optimalshop.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides services for AppUser
 */
@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    private final PasswordEncoder passwordEncoder;
    private final static Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    /**
     *
     * @param username the username identifying the user whose data is required.
     * @return The user details
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optUser = appUserRepository.findAppUserByUsername(username);
        logger.log(Level.INFO, "Utilisateur trouvé : " + optUser.toString());
        UserDetails response = appUserRepository.findAppUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
        return response;
    }

    public AppUserDto getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        AppUser user = appUserRepository.findAppUserByUsername(username).orElseThrow();
        return new AppUserDto(username, user.getEmail(), null);
    }

    /**
     * Retrives the authenticated user and updates its credentials
     * @param userDto
     * @return
     */
    public AppUserDto updateAuthenticatedUser(AppUserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        AppUser user = (AppUser) appUserRepository.findAppUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Utilisateur non trouvé"));
        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        System.out.println(passwordEncoder.encode(userDto.password()));
        user.setPassword(passwordEncoder.encode(userDto.password()));
        appUserRepository.save(user);
        return userDto;
    }

    public boolean deleteAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        AppUser user = appUserRepository.findAppUserByUsername(username).orElseThrow();
        appUserRepository.delete(user);
        return true;
    }
}
