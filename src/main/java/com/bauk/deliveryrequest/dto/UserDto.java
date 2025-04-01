package com.bauk.deliveryrequest.dto;

import com.bauk.deliveryrequest.models.User;

public class UserDto {

    private String name;
    private String email;
    private String password;

    public UserDto() {
        this.name = null;
        this.email = null;
        this.password = null;
    }

    public UserDto(User obj) {
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
