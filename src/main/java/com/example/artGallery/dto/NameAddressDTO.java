package com.example.artGallery.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class NameAddressDTO {
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String address;
}
