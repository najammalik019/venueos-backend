package com.venueos.dto;

import com.venueos.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class AuthDto {

    @Data
    public static class LoginRequest {
        @Email @NotBlank
        private String email;
        @NotBlank
        private String password;
    }

    @Data
    public static class RegisterRequest {
        @NotBlank
        private String firstName;
        @NotBlank
        private String lastName;
        @Email @NotBlank
        private String email;
        @NotBlank
        private String password;
        private String venueName;
        @NotNull
        private Role role;
    }

    @Data
    public static class AuthResponse {
        private String token;
        private String email;
        private String firstName;
        private String lastName;
        private String role;
        private Long userId;

        public AuthResponse(String token, String email, String firstName, String lastName, String role, Long userId) {
            this.token = token;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.role = role;
            this.userId = userId;
        }
    }
}
