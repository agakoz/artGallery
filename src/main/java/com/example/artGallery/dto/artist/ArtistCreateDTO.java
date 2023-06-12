package com.example.artGallery.dto.artist;

import com.example.artGallery.enums.ArtMedium;
import com.example.artGallery.enums.ArtStyle;
import com.example.artGallery.enums.ArtType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtistCreateDTO {
    @NonNull
    String name;

    @NonNull
    String surname;

    @NonNull
    String address;

    @NonNull
    String phone;

    String ssn;

    @NonNull
    String usualType;

    @NonNull
    String usualMedium;

    @NonNull
    String usualStyle;
}
