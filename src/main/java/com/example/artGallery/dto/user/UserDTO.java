package com.example.artGallery.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    int id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String role;
}