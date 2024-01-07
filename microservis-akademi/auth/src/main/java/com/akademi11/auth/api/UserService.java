package com.akademi11.auth.api;

import com.akademi11.auth.impl.User;
import com.akademi11.auth.web.LoginRequest;
import com.akademi11.auth.web.LoginResponse;
import com.akademi11.auth.web.RegisterRequest;

public interface UserService {
    User findById(Long id);

    UserDto find(Long id);

    String register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

}
