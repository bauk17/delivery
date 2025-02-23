package com.bauk.deliveryrequest.dto;

public class AuthResponseDTO {
    private UserResponseDTO user;
    private String token;

    public AuthResponseDTO(String token, UserResponseDTO user) {
        this.token = token;
        this.user = user;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

}