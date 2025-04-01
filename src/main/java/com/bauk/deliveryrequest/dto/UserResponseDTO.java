package com.bauk.deliveryrequest.dto;

import com.bauk.deliveryrequest.models.User;

public class UserResponseDTO {
    private String name;
    private String email;
    private String id;
    private Boolean isAdmin;

    public UserResponseDTO(String name, String email, String id, boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public UserResponseDTO(User user) {
        this.email = user.getEmail();
        this.id = user.getId();
        this.name = user.getName();
        this.isAdmin = user.getIsAdmin();
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

}
