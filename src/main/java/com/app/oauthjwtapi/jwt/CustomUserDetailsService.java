package com.app.oauthjwtapi.jwt;

import com.app.oauthjwtapi.models.entities.User;
import com.app.oauthjwtapi.repositories.IRoleRepository;
import com.app.oauthjwtapi.repositories.IUserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public CustomUserDetailsService(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

    }
    public UserDetails loadOrCreateUserFromOAuth(String email) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(""); // puede estar vacío para OAuth
                    user.setRoles(Set.of(roleRepository.findByName("USER").orElseThrow()));
                    return userRepository.save(user);
                });
    }
}
