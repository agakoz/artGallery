package com.example.artGallery.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
}