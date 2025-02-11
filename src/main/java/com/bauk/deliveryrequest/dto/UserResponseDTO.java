package com.bauk.deliveryrequest.dto;

import com.bauk.deliveryrequest.models.User;

public class UserResponseDTO {
    private String name;
    private String email;
    private String id;

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
