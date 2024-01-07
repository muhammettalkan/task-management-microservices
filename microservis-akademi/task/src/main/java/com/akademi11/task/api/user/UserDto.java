package com.akademi11.task.api.user;

public class UserDto {
    public Long id;
    public String username;
    public String password;
    public String email;

    public UserDto() {
    }

    public UserDto(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
