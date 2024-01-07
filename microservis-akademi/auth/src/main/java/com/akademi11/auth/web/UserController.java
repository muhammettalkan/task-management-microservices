package com.akademi11.auth.web;

import com.akademi11.auth.api.UserDto;
import com.akademi11.auth.api.UserService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id){
        return userService.find(Long.parseLong(id));
    }

}
