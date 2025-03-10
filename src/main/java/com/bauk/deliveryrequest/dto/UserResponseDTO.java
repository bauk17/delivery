package com.bauk.deliveryrequest.dto;

import com.bauk.deliveryrequest.models.User;

public class UserResponseDTO {
    private String name;
    private String email;
    private String id;

    public UserResponseDTO(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public UserResponseDTO(User user) {
        this.email = user.getEmail();
        this.id = user.getId();
        this.name = user.getName();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

}
