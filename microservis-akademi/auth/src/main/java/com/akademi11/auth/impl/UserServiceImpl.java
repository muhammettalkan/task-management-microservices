package com.akademi11.auth.impl;


import com.akademi11.auth.api.UserDto;
import com.akademi11.auth.api.UserService;
import com.akademi11.auth.web.LoginRequest;
import com.akademi11.auth.web.LoginResponse;
import com.akademi11.auth.web.RegisterRequest;
import com.akademi11.library.enums.Role;
import com.akademi11.library.exception.custom.ResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final TaskService taskService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    @Override
    public UserDto find(Long id){
        User user = userRepository.findById(id).orElseThrow();
        return toDto(user);
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email)) {
            throw new RuntimeException("Email is already taken!");
        }

        User user = new User();
        user.setEmail(registerRequest.email);
        user.setPassword(passwordEncoder.encode(registerRequest.password));
        user.setUsername(registerRequest.username);

        Set<Role> authorities = new HashSet<>();
        authorities.add(Role.ROLE_USER);
        user.setAuthorities(authorities);

        user = userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {


        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email , loginRequest.password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findUserByEmail(loginRequest.email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", loginRequest.email)
        );

        LoginResponse response = new LoginResponse();
        response.setAuthorities(user.getAuthorities());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        return response;

    }

    private UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.email = user.getEmail();
        return userDto;
    }
    public User register(UserDto userDto){
        if (userRepository.existsByUsername(userDto.username)){
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(userDto.email)){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setId(userDto.id);
        user.setUsername(userDto.username);
        user.setPassword(userDto.password);
        user.setEmail(userDto.email);
        return user;
    }

}
