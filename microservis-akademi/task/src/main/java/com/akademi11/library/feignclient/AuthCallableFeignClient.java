package com.akademi11.library.feignclient;

import com.akademi11.task.api.user.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface AuthCallableFeignClient {
    @GetMapping("/auth/{id}")
    UserDto getUserById(@PathVariable Long id);
}
