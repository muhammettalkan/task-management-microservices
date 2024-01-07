package com.akademi11.library.security;


import com.akademi11.auth.impl.User;
import com.akademi11.auth.impl.UserRepository;
import com.akademi11.library.exception.custom.ResourceNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(User.class.getSimpleName(), "email", email)
        );

        Set<GrantedAuthority> authorities = user.getAuthorities().stream().map(
                role -> new SimpleGrantedAuthority(role.name())
        ).collect((Collectors.toSet()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
