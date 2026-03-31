package ru.Irina.NauJava.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.Irina.NauJava.entity.User;
import ru.Irina.NauJava.repository.UserRepository;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User appUser = userRepository.findByLogin(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("Пользователь не найден: " + username);
        }


        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + appUser.getRole().name());


        return new org.springframework.security.core.userdetails.User(
                appUser.getLogin(),
                appUser.getPassword(),
                Collections.singletonList(authority)
        );
    }
}