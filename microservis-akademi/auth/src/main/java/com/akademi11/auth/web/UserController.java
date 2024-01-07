package com.akademi11.auth.web;

import com.akademi11.auth.api.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = {"/register", "/signup"})
    public String register(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }


    @PostMapping(value = {"/login", "/signin"})
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

}
